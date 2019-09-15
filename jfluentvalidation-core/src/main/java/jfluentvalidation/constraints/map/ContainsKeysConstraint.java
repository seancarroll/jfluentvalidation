package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// TODO: QUESTION: do we need all the Constains(Entry|Key|Value)Constraint classes?

/**
 *
 * @param <T>  the target type supported by an implementation.
 * @param <K>  the type of keys maintained by this map
 * @param <V>  the type of mapped values
 */
public class ContainsKeysConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final K[] keys;

    public ContainsKeysConstraint(@Nonnull  K[] keys) {
        super(DefaultMessages.MAP_CONTAINS_KEYS);
        this.keys = Ensure.notNull(keys);
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: is this appropriate for this constraint?
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty, then constraint passes but fail if entries is empty and actual is not
        // TODO: does this make sense?
        if (keys.length == 0) {
            return context.getPropertyValue().isEmpty();
        }

        Set<K> notFound = new LinkedHashSet<>();
        for (K key : keys) {
            if (!context.getPropertyValue().containsKey(key)) {
                notFound.add(key);
            }
        }

        if (!notFound.isEmpty()) {
            context.getMessageFormatter().appendArgument("missingKeys", notFound);
        }

        return notFound.isEmpty();
    }
}
