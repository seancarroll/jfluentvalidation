package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

import static java.lang.Character.isWhitespace;

public class IsEqualToIgnoringWhitespaceConstraint implements Constraint<CharSequence> {

    private final CharSequence expected;

    public IsEqualToIgnoringWhitespaceConstraint(CharSequence expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        if (instance == null) return expected == null;
        // checkCharSequenceIsNotNull(expected);
        // return removeAllWhitespaces(actual).equals(removeAllWhitespaces(expected));
        return false;
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
}
