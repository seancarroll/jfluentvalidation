package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code CharSequence} being validated contains only lowercase characters.
 */
public class IsLowerCaseConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        // TODO: should this use locale?
        return value.equals(value.toString().toLowerCase());
    }
}
