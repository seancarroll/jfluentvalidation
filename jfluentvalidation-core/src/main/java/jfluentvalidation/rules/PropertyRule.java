package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.SoftConstraint;
import jfluentvalidation.core.Subject;
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

    private final Subject<?, P> subject;
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;

    public PropertyRule(Subject<?, P> subject) {
        this.subject = subject;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, P> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        P propertyValue = subject.getPropertyFunc().apply(context.getInstanceToValidate());
        for (Constraint<?, ? super P> constraint : subject.getConstraints()) {
            // boolean isValid = constraint.isValid(context.getPropertyValue());
            // TODO: is this the best way to handle this?
            // ValidationContext childContext = new ValidationContext(this);
            RuleContext ruleContext = new RuleContext(context, this);
            boolean isValid = constraint.isValid(ruleContext);
            if (!isValid) {
                String errorMessage = constraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";
                failures.add(new ValidationFailure(subject.getPropertyName(), errorMessage, propertyValue));
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
    public Function<Object, P> getPropertyFunc() {
        return subject.getPropertyFunc();
    }

    // I think there are two separate scenarios for the when clause
    // 1. targeting the instance to validate and used as part of the validator when grouping
    // 2. targeting a subject used as part of the fluent builder
    @Override
    public void applyCondition(Predicate<T> predicate) {
        // TODO: implement
        // throw new RuntimeException("applyCondition is not implemented");
        // TODO: just use a for loop with index instead of having to call indexOf
        for (Constraint constraint : subject.getConstraints()) {
            SoftConstraint softConstraint = new SoftConstraint<>(predicate, constraint);
            int index = subject.getConstraints().indexOf(constraint);
            if (index > -1) {
                subject.getConstraints().toArray()[index] = softConstraint;
            }
        }
    }


}
