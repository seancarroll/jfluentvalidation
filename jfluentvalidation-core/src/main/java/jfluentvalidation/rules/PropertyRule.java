package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.core.Subject;
import jfluentvalidation.validators.ValidationContext;

import java.util.ArrayList;
import java.util.List;
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
        for (Constraint<? super P> constraint : subject.getConstraints()) {
            // boolean isValid = constraint.isValid(context.getPropertyValue());
            boolean isValid = constraint.isValid(propertyValue);
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

    // I think there are two separate scenarios for the when clause
    // 1. targeting the instance to validate and used as part of the validator when grouping
    // 2. targeting a subject used as part of the fluent builder
    @Override
    public void applyCondition(Predicate<T> predicate) {
        // TODO: implement
        throw new RuntimeException("applyCondition is not implemented");
//        for (Constraint<? super P> constraint : subject.getConstraints()) {
//            // SoftConstraint<T> softConstraint = new SoftConstraint<>(predicate, constraint);
//
//        }
    }


}
