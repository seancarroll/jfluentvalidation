package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// TODO: value sucks here...hmmmm
// TODO: QUESTION: do we need all the Constains(Entry|Key|Value)Constraint classes?

/**
 *
 * @param <T>  the target type supported by an implementation.
 * @param <K>  the type of keys maintained by this map
 * @param <V>  the type of mapped values
 */
public class ContainsValuesConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final V[] values;

    public ContainsValuesConstraint(@Nonnull V[] values) {
        super(DefaultMessages.MAP_CONTAINS_VALUES);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(ConstraintContext<T, Map<K, V>> context) {
        // TODO: is this appropriate for this constraint?
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty, then constraint passes but fail if entries is empty and actual is not
        // TODO: does this make sense?
        if (values.length == 0) {
            return context.getPropertyValue().isEmpty();
        }

        Set<V> notFound = new LinkedHashSet<>();
        for (V value : values) {
            if (!context.getPropertyValue().containsValue(value)) {
                notFound.add(value);
            }
        }

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingValues", notFound);
        }

        return notFound.isEmpty();
    }
}
