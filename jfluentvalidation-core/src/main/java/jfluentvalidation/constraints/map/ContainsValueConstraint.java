package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
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
public class ContainsValueConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final V val;

    public ContainsValueConstraint(V val) {
        super(DefaultMessages.MAP_CONTAINS_VALUE);
        this.val = val;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return !context.getPropertyValue().containsValue(val);
    }
}
