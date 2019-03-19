package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsEntryConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsEntry
public class EntryConstraint<K, V> implements Constraint<Map<K, V>> {

    private final Predicate<Map.Entry<K, V>> condition;
    private final Constraint<Map.Entry<K, V>>[] innerConstraints;

    public EntryConstraint(Predicate<Map.Entry<K, V>> condition, Constraint<Map.Entry<K, V>>[] innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(Map<K, V> map) {
        // TODO: fix...need to collect results and return
        for (Map.Entry<K, V> entry : map.entrySet()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition != null && condition.test(entry)) {
                for (Constraint c : innerConstraints) {
                    c.isValid(entry);
                }
            }
        }

        return false;
    }
}
