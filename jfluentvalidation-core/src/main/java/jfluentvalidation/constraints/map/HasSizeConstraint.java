package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

/**
 * Check that the size of the given {@code CharSequence} being validated is equal to the given size.
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class HasSizeConstraint<T, K, V> implements Constraint<T, Map<K, V>> {

    private final int size;

    public HasSizeConstraint(int size) {
        this.size = Ensure.nonnegative(size, "size");
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        return context.getPropertyValue().size() == size;
    }
}
