package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;
import jfluentvalidation.validators.ValidationContext;

import java.util.Map;
import java.util.function.Predicate;

// TODO: just testing this out
// TODO: is there a way to structure this so it could be used by ContainsEntryConstraint?
// Is that even a good idea?
// This looks like a more general case of ContainsEntry
public class EntryConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    private final Predicate<Map.Entry<K, V>> condition;
    private final Constraint<T, Map.Entry<K, V>>[] innerConstraints;

    public EntryConstraint(Predicate<Map.Entry<K, V>> condition, Constraint<T, Map.Entry<K, V>>[] innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        // TODO: fix...need to collect results and return
        for (Map.Entry<K, V> entry : context.getPropertyValue().entrySet()) {
            // TODO: would it be better to default to a AlwaysTrueCondition as a NullableObject instead of null check?
            if (condition != null && condition.test(entry)) {
                for (Constraint c : innerConstraints) {
                    // TODO: is there a better way to do this?
                    ValidationContext childContext = new ValidationContext<>(entry);
                    c.isValid(new RuleContext(childContext, context.getRule()));
                }
            }
        }

        return false;
    }
}
