package jfluentvalidation.rules;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.core.IterableSubject;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

// TODO: should we extend from PropertyRule?

/**
 *
 * @param <T>
 * @param <P>
 */
public class IterablePropertyRule<T, P> implements Rule<T, Iterable<P>> {

    private final IterableSubject<P> subject;
    private final Predicate<P> predicate;
    private List<String> ruleSet = RuleSet.DEFAULT_LIST;

    /**
     *
     */
    public IterablePropertyRule(IterableSubject<P> subject) {
        this(subject, null);
    }

    /**
     *
     * @param predicate
     */
    public IterablePropertyRule(IterableSubject<P> subject, Predicate<P> predicate) {
        this.subject = subject;
        this.predicate = predicate;
    }

    @Override
    public List<ValidationFailure> validate(ValidationContext<T, Iterable<P>> context) {
        List<ValidationFailure> failures = new ArrayList<>();

        Iterable<P> propertyValue = subject.getPropertyFunc().apply(context.getInstanceToValidate());
        for (Constraint<?, ? super Iterable<P>> constraint : subject.getConstraints()) {
            // TODO: is this the best way to handle this?
            // TODO: fix unchecked call
            RuleContext childContext = new RuleContext(context, this);
            if (!constraint.isValid(childContext)) {
                String errorMessage = constraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";
                failures.add(new ValidationFailure(subject.getPropertyName(), errorMessage, propertyValue));
            }
        }

        for (P item : propertyValue) {
            if (predicate == null || predicate.test(item)) {
                for (Constraint<?, ? super P> constraint : subject.getItemConstraints()) {
                    // TODO: is this the best way to handle this?
                    ValidationContext childContext = new ValidationContext(context.getInstanceToValidate());
                    // TODO: our current implementation for forEach adds constraints but doesnt create a new new rul
                    // which means we cant pass this as the rule in
                    if (!constraint.isValid(new RuleContext(childContext, this, item))) {
                        String errorMessage = constraint.getClass().getName() + "." + context.getInstanceToValidate().getClass().getName() + ".";
                        failures.add(new ValidationFailure(subject.getPropertyName(), errorMessage, item));
                    }
                }
            }
        }

        return failures;
    }

    @Override
    public List<String> getRuleSet() {
        return ruleSet;
    }

    @Override
    public void setRuleSet(List<String> ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    public Function<Object, Iterable<P>> getPropertyFunc() {
        return subject.getPropertyFunc();
    }

    @Override
    public void applyCondition(Predicate<T> predicate) {
        // TODO: implement
        throw new RuntimeException("applyCondition is not implemented");
    }

}
