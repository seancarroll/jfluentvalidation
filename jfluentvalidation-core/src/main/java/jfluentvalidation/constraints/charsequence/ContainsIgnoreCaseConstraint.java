package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

// TODO: combine ContainsConstraint and this into a single constraint

/**
 * Checks that the given {@code CharSequence} being validated contains the given sequence, ignoring case.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsIgnoreCaseConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final CharSequence charSequence;

    public ContainsIgnoreCaseConstraint(CharSequence charSequence) {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_IGNORE_CASE);
        this.charSequence = Ensure.notNull(charSequence);
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().toString().toLowerCase().contains(charSequence.toString().toLowerCase());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, A> context) {
        context.getMessageContext().appendArgument("value", charSequence);
    }
}
