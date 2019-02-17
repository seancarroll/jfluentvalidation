package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Checks if a CharSequence is empty (""), null or whitespace only.
 */
public class IsBlankConstraint implements Constraint<CharSequence> {

    // TODO: update logic to not be a copy of commons-lang
    @Override
    public boolean isValid(CharSequence value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
