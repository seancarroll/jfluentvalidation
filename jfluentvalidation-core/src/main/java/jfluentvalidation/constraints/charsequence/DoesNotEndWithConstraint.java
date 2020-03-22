package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated does not end with the given suffix.
 *
 * @param <T>  type of instance to validate.
 */
public class DoesNotEndWithConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final CharSequence suffix;

    public DoesNotEndWithConstraint(CharSequence suffix) {
        super(DefaultMessages.CHARSEQUENCE_DOES_NOT_END_WITH);
        this.suffix = Ensure.notNull(suffix);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !context.getPropertyValue().toString().endsWith(suffix.toString());
    }

    @Override
    public void addParametersToContext(RuleContext<T, A> context) {
        context.getMessageContext().appendArgument("suffix", suffix);
    }
}
