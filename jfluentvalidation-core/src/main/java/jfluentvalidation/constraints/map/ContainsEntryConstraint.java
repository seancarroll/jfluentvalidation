package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;

import java.util.Map;

public class ContainsEntryConstraint<K, V> implements Constraint<Map<K, V>> {

    // TODO: constructor

    @Override
    public boolean isValid(Map<K, V> value) {
        return false;
    }
}
