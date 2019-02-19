package jfluentvalidation.constraints.map;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.util.Map;

/**
 * Check that the size of the given {@code CharSequence} being validated is equal to the given size.
 */
public class HasSizeConstraint implements Constraint<Map<?,?>> {

    private final int size;

    public HasSizeConstraint(int size) {
        this.size = Ensure.nonnegative(size, "size");
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        return value.size() == size;
    }
}
