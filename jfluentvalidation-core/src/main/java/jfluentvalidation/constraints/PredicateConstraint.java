package jfluentvalidation.constraints;

import java.util.function.Predicate;

/**
 *
 * @param <T>
 */
public class PredicateConstraint<T> implements Constraint<T> {

    private final Predicate<T> predicate;

    public PredicateConstraint(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean isValid(T value) {
        return predicate.test(value);
    }
}
