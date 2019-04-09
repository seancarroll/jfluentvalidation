package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

// TODO: generics
public class ContainsKeyConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    // TODO: constructor

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return false;
    }
}
