package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

import java.util.Objects;

/**
 * Check that the given {@code Object} being validated is not equal to the given object.
 * @param <T> the target type supported by an implementation
 */
public class IsNotEqualsConstraint<T> implements Constraint<T> {

    private final T other;

    public IsNotEqualsConstraint(T other) {
        this.other = other;
    }

    @Override
    public boolean isValid(T value) {
        return !Objects.equals(value, other);
    }
}
