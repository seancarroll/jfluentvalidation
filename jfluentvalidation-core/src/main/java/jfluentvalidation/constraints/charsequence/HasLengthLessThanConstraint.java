package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 * Check that the length of the given {@code CharSequence} being validated is less than the given length
 *
 * @param <T>  type of instance to validate.
 */
public class HasLengthLessThanConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final int length;

    public HasLengthLessThanConstraint(int length) {
        super(DefaultMessages.CHARSEQUENCE_HAS_LENGTH_LESS_THAN);
        this.length = length;
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length() < length;
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, A> context) {
        context.getMessageContext().appendArgument("length", length);
    }
}
