package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;

/**
 * Check that the given {@code Map} being validated is not empty.
 */
public class IsNotEmptyConstraint<K, V> implements Constraint<Map<K, V>> {

    @Override
    public boolean isValid(Map<K, V> value) {
        return !value.isEmpty();
    }
}
