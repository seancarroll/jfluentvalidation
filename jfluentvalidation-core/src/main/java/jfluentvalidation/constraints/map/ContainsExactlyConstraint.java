package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static jfluentvalidation.common.Maps.containsEntry;
import static jfluentvalidation.common.Maps.entry;
import static jfluentvalidation.common.Maps.toMap;

/**
 * Check that the actual map contains only the given entries and nothing else, in order.
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class ContainsExactlyConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final Map.Entry<? extends K, ? extends V>[] entries;
    private final Map<? extends K, ? extends V> expected; // a bit harder to confirm keys equal. see below

    // TODO: should we use DefaultMessages.ITERABLE_CONTAINS_EXACTLY?
    public ContainsExactlyConstraint(Map.Entry<? extends K, ? extends V>... entries) {
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY);
        this.entries = Ensure.notNull(entries);
        expected = toMap(entries);
    }

    public ContainsExactlyConstraint(Map<? extends K, ? extends V> expected) {
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY);
        this.expected = Ensure.notNull(expected);
        this.entries = expected.entrySet().toArray(new Map.Entry[0]);
    }


    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: is this appropriate for this constraint?
        if (context.getPropertyValue() == null) {
            return true;
        }

        if (context.getPropertyValue().isEmpty() && entries.length == 0) {
            return true;
        }

//        if (context.getPropertyValue().size() != entries.length) {
//            return false;
//        }

        Set<Map.Entry<? extends K, ? extends V>> notFound = new LinkedHashSet<>();
        Set<Map.Entry<? extends K, ? extends V>> notExpected = new LinkedHashSet<>();

        compareActualMapAndExpectedEntries(context.getPropertyValue(), entries, notExpected, notFound);

        if (notExpected.isEmpty() && notFound.isEmpty()) {
            // check entries order
            int index = 0;
            for (K keyFromActual : context.getPropertyValue().keySet()) {
                if (!Objects.equals(keyFromActual, entries[index].getKey())) {
                    Map.Entry<K, V> actualEntry = entry(keyFromActual, context.getPropertyValue().get(keyFromActual));
                    context.getMessageContext().appendArgument("differentOrderElement", actualEntry);
                    context.getMessageContext().appendArgument("indexOfDifferentElement", index);
//                    context.getMessageContext().appendArgument("missingValues", null);
//                    context.getMessageContext().appendArgument("unexpectedValues", null);
                    return false;
                }
                index++;
            }

            return true;
        }

        // need to set all values to avoid UnresolvablePropertyException when evaluating validation message
//        context.getMessageContext().appendArgument("differentOrderElement", null);
//        context.getMessageContext().appendArgument("indexOfDifferentElement", null);
        context.getMessageContext().appendArgument("missingValues", notFound.isEmpty() ? null : notFound);
        context.getMessageContext().appendArgument("unexpectedValues", notExpected.isEmpty() ? null : notExpected);

        return false;
    }

    private static <K, V> void compareActualMapAndExpectedEntries(Map<K, V> actual,
                                                           Map.Entry<? extends K, ? extends V>[] entries,
                                                           Set<Map.Entry<? extends K, ? extends V>> notExpected,
                                                           Set<Map.Entry<? extends K, ? extends V>> notFound) {
        // TODO: do we want to do this once upon construction and keep as field?
        Map<K, V> expectedEntries = toMap(entries);
        Map<K, V> actualEntries = new LinkedHashMap<>(actual);
        for (Map.Entry<K, V> entry : expectedEntries.entrySet()) {
            if (containsEntry(actualEntries, entry(entry.getKey(), entry.getValue()))) {
                // this is an expected entry
                actualEntries.remove(entry.getKey());
            } else {
                // this is a not found entry
                notFound.add(entry(entry.getKey(), entry.getValue()));
            }
        }

        // All remaining entries from actual copy are not expected entries.
        notExpected.addAll(actualEntries.entrySet());
    }

}
