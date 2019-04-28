package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

// QUESTION: do we need all the Constains(Entry|Key|Value)Constraint classes?

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class ContainsEntryConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    private final Map.Entry<K, V> entry;

    public ContainsEntryConstraint(Map.Entry<K, V> entry) {
        this.entry = entry;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return false;
    }
}
