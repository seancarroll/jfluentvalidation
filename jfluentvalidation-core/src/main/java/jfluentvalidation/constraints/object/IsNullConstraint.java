package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code Object} being validated is {@code null}.
 */
public class IsNullConstraint implements Constraint<Object> {

    @Override
    public boolean isValid(Object value) {
        return value == null;
    }
}
