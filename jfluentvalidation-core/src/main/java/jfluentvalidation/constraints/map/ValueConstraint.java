package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsValueConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsValue
public class ValueConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    private final Predicate<? super V> condition;
    private final Constraint<T, ? super V>[] innerConstraints;

    public ValueConstraint(Predicate<? super V> condition, Constraint<T, ? super V>[] innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: fix...need to collect results and return
        for (V value : context.getPropertyValue().values()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition != null && condition.test(value)) {
                for (Constraint c : innerConstraints) {
                    // TODO: is there a better way to do this?
                    ValidationContext childContext = new ValidationContext(value);
                    c.isValid(new RuleContext(childContext, context.getRule()));
                }
            }
        }

        return false;
    }
}
