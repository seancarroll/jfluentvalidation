package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

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
public class DoesNotContainValuesConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    // TODO: how to handle when given is empty

    private final V[] values;

    public DoesNotContainValuesConstraint(@Nonnull V[] values) {
        super(DefaultMessages.MAP_DOES_NOT_CONTAIN_VALUES);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: is this appropriate for this constraint?
        if (context.getPropertyValue() == null) {
            return true;
        }

        // TODO: handle empty
        if (context.getPropertyValue().isEmpty() && values.length == 0) {
            return true;
        }

        Set<V> found = new LinkedHashSet<>();
        for (V value : values) {
            if (context.getPropertyValue().containsValue(value)) {
                found.add(value);
            }
        }

        if (!found.isEmpty()) {
            context.getMessageFormatter().appendArgument("foundValues", found);
        }

        return found.isEmpty();
    }
}

