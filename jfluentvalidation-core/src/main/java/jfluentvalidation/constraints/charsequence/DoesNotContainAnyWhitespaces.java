package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class DoesNotContainAnyWhitespaces implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence instance) {
        return false;
    }
}
