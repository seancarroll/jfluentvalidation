package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

/**
 * Check that the given {@code Map} being validated is not empty.
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class IsNotEmptyConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    public IsNotEmptyConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return !context.getPropertyValue().isEmpty();
    }
}
