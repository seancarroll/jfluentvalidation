package jfluentvalidation.constraints;

import java.util.function.Predicate;

// QUESTION: Is this a good name?
// fluentvalidation calls this a DelegatingValidator
public class SoftConstraint<T> implements Constraint<T> {

    private final Predicate<T> condition;
    private final Constraint<T> innerConstraint;

    public SoftConstraint(Predicate<T> condition, Constraint<T> innerConstraint) {
        this.condition = condition;
        this.innerConstraint = innerConstraint;
    }

    @Override
    public boolean isValid(T instance) {
        if (condition.test(instance)) {
            return innerConstraint.isValid(instance);
        }

        return true;
    }
}
