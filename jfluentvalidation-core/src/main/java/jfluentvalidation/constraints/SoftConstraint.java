package jfluentvalidation.constraints;

import java.util.function.Predicate;

// QUESTION: Is this a good name?
// fluentvalidation calls this a DelegatingValidator
// QUESTION: is this even a good abstraction?

/**
 *
 * @param <T> the target type supported by an implementation
 */
public class SoftConstraint<T> implements Constraint<T> {

    private final Predicate<? super T> condition;
    private final Constraint<? super T> innerConstraint;

    public SoftConstraint(Predicate<? super T> condition, Constraint<? super T> innerConstraint) {
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
