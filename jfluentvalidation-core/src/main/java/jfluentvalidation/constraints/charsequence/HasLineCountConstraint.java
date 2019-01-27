package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class HasLineCountConstraint implements Constraint<CharSequence> {
    @Override
    public boolean isValid(CharSequence instance) {
        return false;
    }
}
