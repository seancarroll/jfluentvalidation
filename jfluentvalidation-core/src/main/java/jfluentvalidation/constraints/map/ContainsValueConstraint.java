package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

// TODO: value sucks here...hmmmm
// QUESTION: do we need all the Constains(Entry|Key|Value)Constraint classes?

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class ContainsValueConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    private final V val;

    public ContainsValueConstraint(V val) {
        this.val = val;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return !context.getPropertyValue().containsValue(val);
    }
}
