package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

/**
 * Check that the given {@code CharSequence} being validated ends with the given suffix.
 *
 * @param <T>  type of instance to validate.
 */
public class EndsWithConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final CharSequence suffix;

    public EndsWithConstraint(CharSequence suffix) {
        super(DefaultMessages.CHARSEQUENCE_ENDS_WITH);
        this.suffix = Ensure.notNull(suffix);
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().toString().endsWith(suffix.toString());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, A> context) {
        context.getMessageContext().appendArgument("suffix", suffix);
    }
}
