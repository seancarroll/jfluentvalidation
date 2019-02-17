package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class IsEmptyConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return value.toString().isEmpty();
    }

}
