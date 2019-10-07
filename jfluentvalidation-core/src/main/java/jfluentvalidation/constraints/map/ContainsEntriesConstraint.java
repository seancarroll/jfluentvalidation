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

// TODO: QUESTION: do we need all the Constains(Entry|Key|Value)Constraint classes?

/**
 *
 * @param <T>  the target type supported by an implementation.
 * @param <K>  the type of keys maintained by this map
 * @param <V>  the type of mapped values
 */
public class ContainsEntriesConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final Map.Entry<? extends K, ? extends V>[] entries;

    public ContainsEntriesConstraint(@Nonnull Map.Entry<? extends K, ? extends V>[] entries) {
        super(DefaultMessages.MAP_CONTAINS_ENTRIES);
        this.entries = Ensure.notNull(entries);
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: is this appropriate for this constraint?
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty, then constraint passes but fail if entries is empty and actual is not
        // TODO: does this make sense?
        if (entries.length == 0) {
            return context.getPropertyValue().isEmpty();
        }

        Set<Map.Entry<? extends K, ? extends V>> notFound = new LinkedHashSet<>();
        for (Map.Entry<? extends K, ? extends V> entry : entries) {
            if (!containsEntry(context.getPropertyValue(), entry)) {
                notFound.add(entry);
            }
        }

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingEntries", notFound);
        }

        return notFound.isEmpty();
    }

}
