package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

import java.util.Objects;

/**
 * Check that the given {@code Object} being validated is not equal to the given object.
 * @param <S>
 */
public class IsNotEqualsConstraint<S> implements Constraint<S> {

    private final S other;

    public IsNotEqualsConstraint(S other) {
        this.other = other;
    }

    @Override
    public boolean isValid(S value) {
        return !Objects.equals(value, other);
    }
}
