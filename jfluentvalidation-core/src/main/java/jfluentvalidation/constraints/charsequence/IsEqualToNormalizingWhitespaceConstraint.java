package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import static java.lang.Character.isWhitespace;

// TODO: implement!

/**
 *
 * @param <T>  type of instance to validate.
 */
public class IsEqualToNormalizingWhitespaceConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final CharSequence expected;

    public IsEqualToNormalizingWhitespaceConstraint(CharSequence expected) {
        super(DefaultMessages.CHARSEQUENCE_IS_EQUAL_TO_NORMALIZING_WHITESPACE);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {

//        if (actual == null) return expected == null;
//        checkCharSequenceIsNotNull(expected);
//        return normalizeWhitespace(actual).equals(normalizeWhitespace(expected));



        return false;
    }

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
}
