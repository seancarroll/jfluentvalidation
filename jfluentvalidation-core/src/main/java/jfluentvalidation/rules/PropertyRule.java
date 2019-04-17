package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.SoftConstraint;
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
    private Function<T, P> propertyFunc;
    private String propertyName;
    private List<Constraint<?, ? super P>> constraints = new ArrayList<>();
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;

    public PropertyRule(Function<T, P> propertyFunc, String propertyName) {
        this.propertyFunc = propertyFunc;
        this.propertyName = propertyName;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, P> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        P propertyValue = propertyFunc.apply(context.getInstanceToValidate());
        for (Constraint<?, ? super P> constraint : constraints) {
            // TODO: is this the best way to handle this?
            RuleContext ruleContext = new RuleContext(context, this);
            boolean isValid = constraint.isValid(ruleContext);
            if (!isValid) {
                String errorMessage = constraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";
                failures.add(new ValidationFailure(propertyName, errorMessage, propertyValue));
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
        // TODO: implement
        // throw new RuntimeException("applyCondition is not implemented");
        // TODO: just use a for loop with index instead of having to call indexOf
        for (Constraint constraint : constraints) {
            SoftConstraint softConstraint = new SoftConstraint<>(predicate, constraint);
            int index = constraints.indexOf(constraint);
            if (index > -1) {
                constraints.toArray()[index] = softConstraint;
            }
        }
    }

    public void addConstraint(Constraint<?, P> constraint) {
        constraints.add(constraint);
    }

}
