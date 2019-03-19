package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code Object} being validated is not {@code null}.
 * @param <T> the target type supported by an implementation
 */
public class IsNotNullConstraint<T> implements Constraint<T> {

    @Override
    public boolean isValid(T value) {
        return value != null;
    }
}
