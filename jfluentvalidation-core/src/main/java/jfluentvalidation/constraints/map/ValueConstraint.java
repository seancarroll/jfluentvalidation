package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsValueConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsValue

/**
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class ValueConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final Predicate<? super V> condition;
    private final Constraint<T, ? super V>[] innerConstraints;

    public ValueConstraint(Constraint<T, ? super V>[] innerConstraints) {
        this(null, innerConstraints);
    }

    public ValueConstraint(Predicate<? super V> condition, Constraint<T, ? super V>[] innerConstraints) {
        super(DefaultMessages.MAP_VALUE);
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: fix...need to collect results and return
        for (V value : context.getPropertyValue().values()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition == null || condition.test(value)) {
                for (Constraint c : innerConstraints) {
                    // TODO: is there a better way to do this?
                    ValidationContext childContext = new ValidationContext(value);
                    return c.isValid(new RuleContext(childContext, context.getRule(), value));
                }
            }
        }

        return false;
    }
}
