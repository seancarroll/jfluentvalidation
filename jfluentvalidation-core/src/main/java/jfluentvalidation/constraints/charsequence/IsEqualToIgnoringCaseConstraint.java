package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 * Checks that the given {@code CharSequence} being validated equals the given sequence, ignoring case.
 *
 * @param <T>  type of instance to validate.
 */
public class IsEqualToIgnoringCaseConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final CharSequence other;

    public IsEqualToIgnoringCaseConstraint(CharSequence other) {
        super(DefaultMessages.CHARSEQUENCE_IS_EQUAL_TO_IGNORING_CASE);
        this.other = other;
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return other == null;
        }
        if (other == null) {
            return false;
        }
        return context.getPropertyValue().toString().equalsIgnoreCase(other.toString());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, A> context) {
        context.getMessageContext().appendArgument("other", other);
    }
}
