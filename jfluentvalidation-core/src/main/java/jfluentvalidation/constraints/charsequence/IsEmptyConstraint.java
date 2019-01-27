package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class IsEmptyConstraint implements Constraint<CharSequence> {
    @Override
    public boolean isValid(CharSequence instance) {
        return instance.toString().isEmpty();
    }
}
