package jfluentvalidation.constraints.array.sizebetween;

// TODO: does this boolean for whether or not we should include/exlude boundaries?

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the length of the given {@code Array} being validated is between the given min and max boundaries (inclusive).
 * @param <T>
 */
public class HasSizeBetweenFloatArrayConstriant<T> implements Constraint<T, boolean[]> {

    private final int min;
    private final int max;

    /**
     *
     * @param min the min boundary compared to which actual length should be greater than or equal to.
     * @param max the max boundary compared to which actual length should be less than or equal to.
     */
    public HasSizeBetweenFloatArrayConstriant(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        int len = context.getPropertyValue().length;
        return len >= min && len <= max;
    }
}
