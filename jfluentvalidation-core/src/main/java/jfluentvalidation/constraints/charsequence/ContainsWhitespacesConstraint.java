package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code CharSequence} being validated contains one or more whitespace characters.
 */
public class ContainsWhitespacesConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return !Strings.isNullOrEmpty(value) && Strings.containsWhitespaces(value);
    }
}
