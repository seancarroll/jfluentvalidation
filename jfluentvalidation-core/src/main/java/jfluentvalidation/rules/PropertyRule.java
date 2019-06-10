package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.SoftConstraint;
import jfluentvalidation.validators.PropertyLiteralHelper;
import jfluentvalidation.validators.RuleContext;
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
    private List<Constraint<?, ? extends P>> constraints = new ArrayList<>();
    private Constraint<?, ? extends P> currentConstraint;
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;

    public PropertyRule(Function<T, P> propertyFunc, String propertyName) {
        this.propertyFunc = propertyFunc;
        this.propertyName = propertyName;
    }

    public PropertyRule(Class<T> type, Function<T, P> propertyFunc) {
        T proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, propertyFunc);
        this.propertyFunc = propertyFunc;
        this.propertyName = propertyName;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, P> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        // TODO: whats the difference between context.getPropertyValue() and propertyFunc.apply(context.getInstanceToValidate())
        // I would think context.getProperty would give us the appropriate value without having to do the func
        // TODO: fix this
        P propertyValue = propertyFunc.apply(context.getInstanceToValidate());
        for (Constraint<?, ? extends P> constraint : constraints) {
            // TODO: is this the best way to handle this?
            RuleContext ruleContext = new RuleContext(context, this);
            boolean isValid = constraint.isValid(ruleContext);
            if (!isValid) {
//                String errorMessage = constraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";
                ruleContext.appendArgument("PropertyName", ruleContext.getRule().getPropertyName());
                ruleContext.appendArgument("PropertyValue", ruleContext.getPropertyValue());

                failures.add(new ValidationFailure(propertyName, constraint.getOptions().getErrorMessage(), propertyValue));
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

    // I think there are two separate scenarios for the when clause
    // 1. targeting the instance to validate and used as part of the validator when grouping
    // 2. targeting a subject used as part of the fluent builder
    @Override
    public void applyCondition(Predicate<T> predicate) {
        // TODO: need to add ability to apply to only one constraint vs all constraints
        for (Constraint constraint : constraints) {
            SoftConstraint softConstraint = new SoftConstraint<>(predicate, constraint);
            int index = constraints.indexOf(constraint);
            if (index > -1) {
                constraints.toArray()[index] = softConstraint;
            }
        }
    }

    @Override
    public List<Constraint<?, ? extends P>> getConstraints() {
        return constraints;
    }

    // TODO: should this just be addConstraints and take a varargs?
    public void addConstraint(Constraint<T, P> constraint) {
        currentConstraint = constraint;
        constraints.add(constraint);
    }

    public Constraint<?, ? extends P> getCurrentConstraint() {
        return currentConstraint;
    }

}
