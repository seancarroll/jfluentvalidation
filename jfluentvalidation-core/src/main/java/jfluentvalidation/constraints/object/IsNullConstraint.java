package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code Object} being validated is {@code null}.
 * @param <T> the target type supported by an implementation
 */
public class IsNullConstraint<T> implements Constraint<T> {

    @Override
    public boolean isValid(T value) {
        return value == null;
    }
}
