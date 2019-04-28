package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks that the given {@code CharSequence} being validated starts with the given prefix.
 * @param <T> type of instance to validate
 */
public class StartsWithConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence prefix;
    private final int offset;

    public StartsWithConstraint(CharSequence prefix) {
        this(prefix, 0);
    }

    public StartsWithConstraint(CharSequence prefix, int offset) {
        this.prefix = Ensure.notNull(prefix);
        this.offset = Ensure.nonnegative(offset, "offset");
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        // TODO: this probably should be based on a comparison strategy
        return validationContext.getPropertyValue().toString().startsWith(prefix.toString(), offset);
    }
}
