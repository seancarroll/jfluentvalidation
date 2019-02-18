package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

import java.util.Objects;

/**
 * Check that the given {@code Object} being validated is not equal to the given object.
 */
public class IsNotEqualsConstraint implements Constraint<Object> {

    private final Object other;

    public IsNotEqualsConstraint(Object other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Object value) {
        return !Objects.equals(value, other);
    }
}
