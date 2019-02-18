package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class IsUpperCaseConstraint implements Constraint<CharSequence> {

    @Override
    public boolean isValid(CharSequence value) {
        // TODO: should this use locale?
        return value.equals(value.toString().toUpperCase());
    }
}
