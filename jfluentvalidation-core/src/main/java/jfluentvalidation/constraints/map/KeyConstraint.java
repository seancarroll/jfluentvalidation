package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsKeyConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsKey

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class KeyConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final Predicate<? super K> condition;
    private final Constraint<T, ? super K>[] innerConstraints;

    public KeyConstraint(Constraint<T, ? super K>[] innerConstraints) {
        this(null, innerConstraints);
    }

    public KeyConstraint(Predicate<? super K> condition, Constraint<T, ? super K>[] innerConstraints) {
        super(DefaultMessages.MAP_KEY);
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: fix...need to collect results and return
        // I Think we should return a failure for each item that fails however its impossible to do that when returning boolean
        for (K key : context.getPropertyValue().keySet()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition == null || condition.test(key)) {
                for (Constraint c : innerConstraints) {
                    // TODO: is there a better way to do this?
                    // TODO: fix generic
                    ValidationContext childContext = new ValidationContext<>(key);
                    return c.isValid(new RuleContext(childContext, context.getRule(), key));
                }
            }
        }

        return false;
    }
}
