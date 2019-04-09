package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsKeyConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsKey
public class KeyConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    private final Predicate<? super K> condition;
    private final Constraint<T, ? super K>[] innerConstraints;

    public KeyConstraint(Predicate<? super K> condition, Constraint<T, ? super K>[] innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: fix...need to collect results and return
        for (K key : context.getPropertyValue().keySet()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition != null && condition.test(key)) {
                for (Constraint c : innerConstraints) {
                    // TODO: is there a better way to do this?
                    ValidationContext childContext = new ValidationContext<>(key);
                    c.isValid(new RuleContext(childContext, context.getRule()));
                }
            }
        }

        return false;
    }
}
