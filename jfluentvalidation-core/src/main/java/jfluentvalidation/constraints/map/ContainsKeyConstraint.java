package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;

// TODO: generics
public class ContainsKeyConstraint<K, V> implements Constraint<Map<K, V>> {

    // TODO: constructor

    @Override
    public boolean isValid(Map<K, V> value) {
        return false;
    }
}
