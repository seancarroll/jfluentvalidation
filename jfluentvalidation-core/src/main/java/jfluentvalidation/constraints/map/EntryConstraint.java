package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsEntryConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsEntry

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class EntryConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    // TODO: can we take care of this by using a SoftConstraint?
    private final Predicate<Map.Entry<K, V>> condition;
    private final Constraint<T, Map.Entry<K, V>>[] innerConstraints;

    public EntryConstraint(Constraint<T, Map.Entry<K, V>>[] innerConstraints) {
        this(null, innerConstraints);
    }

    public EntryConstraint(Predicate<Map.Entry<K, V>> condition, Constraint<T, Map.Entry<K, V>>[] innerConstraints) {
        super(DefaultMessages.MAP_ENTRY);
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: fix...need to collect results and return
        for (Map.Entry<K, V> entry : context.getPropertyValue().entrySet()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition == null || condition.test(entry)) {
                for (Constraint c : innerConstraints) {
                    // TODO: is there a better way to do this?
                    ValidationContext childContext = new ValidationContext<>(entry);
                    return c.isValid(new RuleContext(childContext, context.getRule(), entry));
                }
            }
        }

        return false;
    }
}
