package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsKeyConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsKey
public class KeyConstraint<K, V> implements Constraint<Map<K, V>> {

    private final Predicate<? super K> condition;
    private final Constraint<? super K>[] innerConstraints;

    public KeyConstraint(Predicate<? super K> condition, Constraint<? super K>[] innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(Map<K, V> map) {
        // TODO: fix...need to collect results and return
        for (K key : map.keySet()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition != null && condition.test(key)) {
                for (Constraint c : innerConstraints) {
                    c.isValid(key);
                }
            }
        }

        return false;
    }
}
