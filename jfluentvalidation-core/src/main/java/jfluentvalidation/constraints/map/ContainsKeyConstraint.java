package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

// QUESTION: do we need all the Constains(Entry|Key|Value)Constraint classes?

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class ContainsKeyConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final K key;

    public ContainsKeyConstraint(K key) {
        super(DefaultMessages.MAP_CONTAINS_KEY);
        this.key = key;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return false;
    }
}
