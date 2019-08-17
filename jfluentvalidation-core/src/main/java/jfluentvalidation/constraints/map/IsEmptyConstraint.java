package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

/**
 * Check that the given {@code Map} being validated is empty.
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class IsEmptyConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    public IsEmptyConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isEmpty();
    }
}
