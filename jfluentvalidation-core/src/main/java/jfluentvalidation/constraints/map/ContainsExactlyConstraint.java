package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.validators.RuleContext;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static jfluentvalidation.common.Maps.containsEntry;
import static jfluentvalidation.common.Maps.entry;
import static jfluentvalidation.common.Maps.toMap;

// TODO: have a base class for array, iterable, map???
// TODO: test this

/**
 * Check that the actual map contains only the given entries and nothing else, in order.
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class ContainsExactlyConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    // TODO: also allow another map?
    private  Map.Entry<? extends K, ? extends V>[] entries;
    private final Map<? extends K, ? extends V> expected; // a bit harder to confirm keys equal. see below

    public ContainsExactlyConstraint(Map.Entry<? extends K, ? extends V>... entries) {
        super("");
        this.entries = entries;

        expected = toMap(entries);
    }

    public ContainsExactlyConstraint(Map<? extends K, ? extends V> expected) {
        super("");
        this.expected = expected;
    }


    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        //doCommonContainsCheck(info, actual, entries);

        if (context.getPropertyValue().isEmpty() && entries.length == 0) {
            return true;
        }

        // fail if entries is empty
        // assert same size

        Set<Map.Entry<? extends K, ? extends V>> notFound = new LinkedHashSet<>();
        Set<Map.Entry<? extends K, ? extends V>> notExpected = new LinkedHashSet<>();

        compareActualMapAndExpectedEntries(context.getPropertyValue(), entries, notExpected, notFound);

        if (notExpected.isEmpty() && notFound.isEmpty()) {
            // check entries order
            int index = 0;
            for (K keyFromActual : context.getPropertyValue().keySet()) {
                // this check is a bit harder to do with a map. see what options we have
                if (!Objects.equals(keyFromActual, entries[index].getKey())) {
                    Map.Entry<K, V> actualEntry = entry(keyFromActual, context.getPropertyValue().get(keyFromActual));
                    // TODO: set name of argument
                    context.getMessageContext().appendArgument("", actualEntry);
                    return false;
                }
                index++;
            }

            return true;
        }

        context.getMessageContext().appendArgument("notFound", notFound);
        context.getMessageContext().appendArgument("notExpected", notExpected);
        return false;
    }

    private <K, V> void compareActualMapAndExpectedEntries(Map<K, V> actual,
                                                           Map.Entry<? extends K, ? extends V>[] entries,
                                                           Set<Map.Entry<? extends K, ? extends V>> notExpected,
                                                           Set<Map.Entry<? extends K, ? extends V>> notFound) {

//        Does Sets.intersection help at all?
//        // We're using the fact that Sets.intersection keeps the order of the first set.
//        List<?> expectedKeyOrder =
//            Lists.newArrayList(Sets.intersection(expectedMap.keySet(), actual.keySet()));
//        List<?> actualKeyOrder =
//            Lists.newArrayList(Sets.intersection(actual.keySet(), expectedMap.keySet()));

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
        // TODO: this copy feels unnecessary...
        notExpected.addAll(actualEntries.entrySet());
    }


//
//    private <K, V> void doCommonContainsCheck(AssertionInfo info, Map<K, V> actual,
//                                              Map.Entry<? extends K, ? extends V>[] entries) {
//        assertNotNull(info, actual);
//        failIfNull(entries);
//    }
}
