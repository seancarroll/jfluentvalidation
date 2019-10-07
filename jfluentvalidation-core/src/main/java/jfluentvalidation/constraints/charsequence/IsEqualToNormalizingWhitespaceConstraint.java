package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import static java.lang.Character.isWhitespace;

/**
 *
 * @param <T>  type of instance to validate.
 */
public class IsEqualToNormalizingWhitespaceConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final CharSequence expected;

    public IsEqualToNormalizingWhitespaceConstraint(CharSequence expected) {
        super(DefaultMessages.CHARSEQUENCE_IS_EQUAL_TO_NORMALIZING_WHITESPACE);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return expected == null;
        }
        if (expected == null) {
            return false;
        }
        return normalizeWhitespace(context.getPropertyValue()).equals(normalizeWhitespace(expected));
    }

    // TODO: change to not be a complete copy of assertj
    private String normalizeWhitespace(CharSequence toNormalize) {
        final StringBuilder result = new StringBuilder(toNormalize.length());
        boolean lastWasSpace = true;
        for (int i = 0; i < toNormalize.length(); i++) {
            char c = toNormalize.charAt(i);
            if (isWhitespace(c)) {
                if (!lastWasSpace) result.append(' ');
                lastWasSpace = true;
            } else {
                result.append(c);
                lastWasSpace = false;
            }
        }
        return result.toString().trim();
    }

    @Override
    public void addParametersToContext(RuleContext<T, A> context) {
        context.getMessageContext().appendArgument("expected", expected);
    }
}
