package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;

// TODO: value sucks here...hmmmm
public class ContainsValueConstraint<K, V> implements Constraint<Map<K,V>> {

    private final V val;

    public ContainsValueConstraint(V val) {
        this.val = val;
    }

    @Override
    public boolean isValid(Map<K, V> value) {
        return !value.containsValue(val);
    }
}
