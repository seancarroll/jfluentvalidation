package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class ContainsWhitespacesConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        return false;
    }
}
