package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @param <T>  the target type supported by an implementation.
 * @param <K>  the type of keys maintained by this map
 * @param <V>  the type of mapped values
 */
public class DoesNotContainKeysConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    // TODO: how to handle when given is empty

    private final K[] keys;

    public DoesNotContainKeysConstraint(@Nonnull K[] keys) {
        super(DefaultMessages.MAP_DOES_NOT_CONTAIN_KEYS);
        this.keys = Ensure.notNull(keys);
    }

    @Override
    public boolean isValid(ConstraintContext<T, Map<K, V>> context) {
        // TODO: is this appropriate for this constraint?
        if (context.getPropertyValue() == null) {
            return true;
        }

        // TODO: handle empty
        if (context.getPropertyValue().isEmpty() && keys.length == 0) {
            return true;
        }

        Set<K> found = new LinkedHashSet<>();
        for (K key : keys) {
            if (context.getPropertyValue().containsKey(key)) {
                found.add(key);
            }
        }

        if (!found.isEmpty()) {
            context.getMessageContext().appendArgument("foundKeys", found);
        }

        return found.isEmpty();
    }
}
