package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import static java.lang.Character.isWhitespace;

/**
 *
 * @param <T>  type of instance to validate.
 */
public class IsEqualToIgnoringWhitespaceConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final CharSequence expected;

    public IsEqualToIgnoringWhitespaceConstraint(CharSequence expected) {
        super(DefaultMessages.CHARSEQUENCE_IS_EQUAL_TO_IGNORING_WHITESPACE);
        this.expected = expected;
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return expected == null;
        }
        if (expected == null) {
            return false;
        }
        return removeAllWhitespaces(context.getPropertyValue()).equals(removeAllWhitespaces(expected));
    }


    private String removeAllWhitespaces(CharSequence toBeStripped) {
        // TODO: performance test these with whats below
        // st.replaceAll("\\s+","") and st.replaceAll("\\s","") produce the same result.

        final StringBuilder result = new StringBuilder(toBeStripped.length());
        for (int i = 0; i < toBeStripped.length(); i++) {
            char c = toBeStripped.charAt(i);
            if (isWhitespace(c)) {
                continue;
            }
            result.append(c);
        }
        return result.toString();
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, A> context) {
        context.getMessageContext().appendArgument("expected", expected);
    }
}
