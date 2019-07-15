package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;
import java.util.Objects;

// QUESTION: do we need all the Constains(Entry|Key|Value)Constraint classes?

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class ContainsEntryConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final Map.Entry<K, V> entry;

    public ContainsEntryConstraint(Map.Entry<K, V> entry) {
        super(DefaultMessages.MAP_CONTAINS_ENTRY);
        this.entry = entry;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().containsKey(entry.getKey())
            && Objects.equals(context.getPropertyValue().get(entry.getKey()), entry.getValue());
    }
}
