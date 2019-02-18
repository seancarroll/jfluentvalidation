package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code CharSequence} being validated is empty.
 */
public class IsEmptyConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return value.toString().isEmpty();
    }

}
