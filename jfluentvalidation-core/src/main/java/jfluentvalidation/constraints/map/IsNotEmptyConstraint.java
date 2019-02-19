package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;

/**
 * Check that the given {@code Map} being validated is not empty.
 */
public class IsNotEmptyConstraint implements Constraint<Map<?,?>> {

    @Override
    public boolean isValid(Map<?, ?> value) {
        return !value.isEmpty();
    }
}
