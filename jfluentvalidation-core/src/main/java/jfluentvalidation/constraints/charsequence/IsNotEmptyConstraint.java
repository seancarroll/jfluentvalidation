package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code CharSequence} being validated is not empty.
 */
public class IsNotEmptyConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return !value.toString().isEmpty();
    }
}
