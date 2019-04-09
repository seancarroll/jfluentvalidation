package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

/**
 * Check that the given {@code Map} being validated is not empty.
 */
public class IsNotEmptyConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return !context.getPropertyValue().isEmpty();
    }
}
