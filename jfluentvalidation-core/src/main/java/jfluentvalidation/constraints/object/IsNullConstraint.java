package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code Object} being validated is {@code null}.
 * @param <S>
 */
public class IsNullConstraint<S> implements Constraint<S> {

    @Override
    public boolean isValid(S value) {
        return value == null;
    }
}
