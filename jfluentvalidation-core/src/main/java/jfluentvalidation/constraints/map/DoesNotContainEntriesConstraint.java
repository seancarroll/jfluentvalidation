package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static jfluentvalidation.common.Maps.containsEntry;

/**
 *
 * @param <T>  the target type supported by an implementation.
 * @param <K>  the type of keys maintained by this map
 * @param <V>  the type of mapped values
 */
public class DoesNotContainEntriesConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    // TODO: how to handle when given is empty...how does map handle contains null/empty?

    private final Map.Entry<? extends K, ? extends V>[] entries;

    // TODO: how do we want to handle null and empty entries?
    public DoesNotContainEntriesConstraint(@Nonnull Map.Entry<? extends K, ? extends V>[] entries) {
        super(DefaultMessages.MAP_DOES_NOT_CONTAIN_ENTRIES);
        this.entries = Ensure.notNullOrEmpty(entries);
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: is this appropriate for this constraint?
        if (context.getPropertyValue() == null) {
            return true;
        }

        // TODO: handle empty
        if (context.getPropertyValue().isEmpty() && entries.length == 0) {
            return true;
        }

        Set<Map.Entry<? extends K, ? extends V>> found = new LinkedHashSet<>();
        for (Map.Entry<? extends K, ? extends V> entry : entries) {
            if (containsEntry(context.getPropertyValue(), entry)) {
                found.add(entry);
            }
        }

        if (!found.isEmpty()) {
            context.getMessageContext().appendArgument("foundEntries", found);
        }

        return found.isEmpty();
    }
}
