package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class IsUpperCaseConstraint implements Constraint<CharSequence> {
    @Override
    public boolean isValid(CharSequence instance) {
        // TODO: should this use locase?
        return instance.equals(instance.toString().toUpperCase());
    }
}
