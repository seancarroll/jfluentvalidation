package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Comparables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

// TODO: length constraint where the users specify inclusive/exclusive boundaries

/**
 * Check that the length of the given {@code CharSequence} being validated is hasLengthBetween the given lower and higher boundaries (inclusive).
 *
 * @param <T>  type of instance to validate.
 */
public class HasLengthBetweenConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final int min;
    private final int max;
    private final boolean inclusiveStart;
    private final boolean inclusiveEnd;

    public HasLengthBetweenConstraint(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        super(DefaultMessages.CHARSEQUENCE_HAS_LENGTH_BETWEEN);
        Ensure.argument(min <= max, "hasMinLength must be less than or equal to hasMaxLength");
        this.min = min;
        this.max = max;
        this.inclusiveStart = inclusiveStart;
        this.inclusiveEnd = inclusiveEnd;

    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length();
        return Comparables.isBetween(len, min, max, inclusiveStart, inclusiveEnd);
    }
}
