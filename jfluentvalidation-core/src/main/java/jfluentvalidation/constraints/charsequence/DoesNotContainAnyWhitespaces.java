package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code CharSequence} being validated is {@code Null}, empty or contains only non-whitespace characters.
 */
public class DoesNotContainAnyWhitespaces implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return !Strings.isNullOrEmpty(value) && !Strings.containsWhitespaces(value);
    }
}
