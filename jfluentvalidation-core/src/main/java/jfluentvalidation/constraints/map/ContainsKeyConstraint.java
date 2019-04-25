package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

// QUESTION: do we need all the Constains(Entry|Key|Value)Constraint classes?
public class ContainsKeyConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    private final K key;

    public ContainsKeyConstraint(K key) {
        this.key = key;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return false;
    }
}
