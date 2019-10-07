package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks that the given {@code CharSequence} being validated starts with the given prefix.
 *
 * @param <T>  type of instance to validate.
 */
public class StartsWithConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final CharSequence prefix;
    private final int offset;

    public StartsWithConstraint(CharSequence prefix) {
        this(prefix, 0);
    }

    public StartsWithConstraint(CharSequence prefix, int offset) {
        super(DefaultMessages.CHARSEQUENCE_STARTS_WITH);
        this.prefix = Ensure.notNull(prefix);
        this.offset = Ensure.nonnegative(offset, "offset");
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        // TODO: this probably should be based on a comparison strategy
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().toString().startsWith(prefix.toString(), offset);
    }


    @Override
    public void addParametersToContext(RuleContext<T, A> context) {
        context.getMessageContext().appendArgument("prefix", prefix);
        context.getMessageContext().appendArgument("offset", offset);
    }
}
