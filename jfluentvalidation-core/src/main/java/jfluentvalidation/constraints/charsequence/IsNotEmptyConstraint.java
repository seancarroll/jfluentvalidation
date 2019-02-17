package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class IsNotEmptyConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return !value.toString().isEmpty();
    }
}
