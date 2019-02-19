package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;

/**
 * Check that the given {@code Map} being validated is empty.
 */
public class IsEmptyConstraint implements Constraint<Map<?,?>> {

    @Override
    public boolean isValid(Map<?, ?> value) {
        return value.isEmpty();
    }
}
