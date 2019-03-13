package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code Object} being validated is not {@code null}.
 * @param <S>
 */
public class IsNotNullConstraint<S> implements Constraint<S> {

    @Override
    public boolean isValid(S value) {
        return value != null;
    }
}
