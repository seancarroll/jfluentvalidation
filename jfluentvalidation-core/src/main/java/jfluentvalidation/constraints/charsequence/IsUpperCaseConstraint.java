package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code CharSequence} being validated contains only uppercase characters.
 */
public class IsUpperCaseConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        // TODO: should this use locale?
        return value.equals(value.toString().toUpperCase());
    }
}
