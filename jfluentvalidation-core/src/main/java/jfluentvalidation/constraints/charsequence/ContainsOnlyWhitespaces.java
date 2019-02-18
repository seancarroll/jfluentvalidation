package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code CharSequence} being validated consists of one or more whitespace characters.
 */
public class ContainsOnlyWhitespaces implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return !Strings.isNullOrEmpty(value) && Strings.containsOnlyWhitespace(value);
    }
}
