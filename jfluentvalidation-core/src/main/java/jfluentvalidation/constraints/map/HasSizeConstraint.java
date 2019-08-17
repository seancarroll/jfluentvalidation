package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

/**
 * Check that the size of the given {@code Map} being validated is equal to the given size.
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class HasSizeConstraint<T, K, V> extends AbstractConstraint<T, Map<K, V>> {

    private final int size;

    public HasSizeConstraint(int size) {
        super(DefaultMessages.HAS_SIZE);
        this.size = Ensure.nonnegative(size, "size");
    }

    @Override
    public boolean isValid(RuleContext<T, Map<K, V>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().size() == size;
    }
}
