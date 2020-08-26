package jfluentvalidation.rules;

import jfluentvalidation.PropertyNameExtractor;
import jfluentvalidation.SerializableFunction;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.SoftConstraint;
import jfluentvalidation.messageinterpolation.ResourceBundleMessageInterpolator;
import jfluentvalidation.validators.ConstraintContext;
import jfluentvalidation.validators.RuleOptions;
import jfluentvalidation.validators.ValidationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @param <T>
 * @param <P>
 */
public class PropertyRule<T, P> implements Rule<T, P> {

    // TODO: what if we replaced subject with list of constraints, the property func, and property name?
    // problem being is how do we add constraints if not through the subject given it acts as our connector?
    // could we have flip it and instead have Subject contain a Rule/PropertyRule?
    protected Function<T, P> propertyFunc;
    protected String propertyName;
    protected RuleOptions ruleOptions;

    // TODO: ResourceBundleMessageInterpolator could be a singleton?
    private ResourceBundleMessageInterpolator interpolator = new ResourceBundleMessageInterpolator();
    private List<Constraint<T, P>> constraints = new ArrayList<>();
    private Constraint<T, P> currentConstraint;
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;

    public PropertyRule(Function<T, P> propertyFunc, String propertyName, RuleOptions ruleOptions) {
        this.propertyFunc = propertyFunc;
        this.propertyName = propertyName;
        this.ruleOptions = ruleOptions;
    }

    public PropertyRule(Class<T> type, SerializableFunction<T, P> propertyFunc, RuleOptions ruleOptions) {
        this.propertyFunc = propertyFunc;
        this.propertyName = PropertyNameExtractor.getInstance().getPropertyName(type, propertyFunc);
        this.ruleOptions = ruleOptions;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T> context) {
        List<ValidationFailure> failures = new ArrayList<>();
        P propertyValue = propertyFunc.apply(context.getInstanceToValidate());
        for (Constraint<T, P> constraint : constraints) {
            // TODO: is this the best way to handle this?
            ConstraintContext<T, P> constraintContext = new ConstraintContext<>(context, this);
            boolean isValid = constraint.isValid(constraintContext);
            if (!isValid) {
                constraintContext.getMessageContext().appendPropertyName(constraintContext.getRule().getPropertyName());
                constraintContext.getMessageContext().appendPropertyValue(constraintContext.getPropertyValue());
                constraint.addParametersToContext(constraintContext);

                String resolvedMessage = interpolator.interpolate(
                    constraint.getOptions().getErrorMessage(),
                    constraintContext.getMessageContext().getPlaceholderValues()
                );

                failures.add(new ValidationFailure(propertyName, resolvedMessage, propertyValue));
            }
        }

        return failures;
    }

    // TODO: do we need to expose subject? How can we apply a predicate and have it propagate appropriately?
    // Do we have something like FluentValidation's ApplyCondition method to this class?

    @Override
    public List<String> getRuleSet() {
        return ruleSet;
    }

    @Override
    public void setRuleSet(List<String> ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    public Function<T, P> getPropertyFunc() {
        return propertyFunc;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    public RuleOptions getRuleOptions() {
        return ruleOptions;
    }

    // TODO: test this...
    // TODO: swap out boolean with enum
    // I think there are two separate scenarios for the when clause
    // 1. targeting the instance to validate and used as part of the validator when grouping
    // 2. targeting a subject used as part of the fluent builder
    @Override
    public void applyCondition(Predicate<T> predicate, boolean applyToAll) {
        if (applyToAll) {
            for (Constraint<T, P> constraint : constraints) {
                SoftConstraint<T, P> softConstraint = new SoftConstraint<>(predicate, constraint);
                int index = constraints.indexOf(constraint);
                if (index > -1) {
                    constraints.set(index, softConstraint);
                }
            }
        } else {
            int index = constraints.indexOf(currentConstraint);
            if (index > -1) {
                constraints.set(index, new SoftConstraint<>(predicate, currentConstraint));
            }
        }
    }

    @Override
    public List<Constraint<T, P>> getConstraints() {
        return constraints;
    }

    // TODO: should this just be addConstraints and take a varargs?
    public void addConstraint(Constraint<T, P> constraint) {
        currentConstraint = constraint;
        constraints.add(constraint);
    }

    public Constraint<T, P> getCurrentConstraint() {
        return currentConstraint;
    }

}
