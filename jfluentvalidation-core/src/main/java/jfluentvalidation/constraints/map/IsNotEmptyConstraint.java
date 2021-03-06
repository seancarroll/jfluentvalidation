package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import java.util.Map;

/**
 * Check that the given {@code Map} being validated is not empty.
 *
 * @param <T>  the target type supported by an implementation.
 * @param <K>  the type of keys maintained by this map
 * @param <V>  the type of mapped values
 */
public class IsNotEmptyConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    public IsNotEmptyConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, Map<K, V>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !context.getPropertyValue().isEmpty();
    }
}
