package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsValueConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsValue
public class ValueConstraint<K, V> implements Constraint<Map<K, V>> {

    private final Predicate<? super V> condition;
    private final Constraint<? super V>[] innerConstraints;

    public ValueConstraint(Predicate<? super V> condition, Constraint<? super V>[] innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(Map<K, V> map) {
        // TODO: fix...need to collect results and return
        for (V value : map.values()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition != null && condition.test(value)) {
                for (Constraint c : innerConstraints) {
                    c.isValid(value);
                }
            }
        }

        return false;
    }
}
