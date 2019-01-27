package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class IsLowerCaseConstraint implements Constraint<CharSequence> {
    @Override
    public boolean isValid(CharSequence instance) {
        // TODO: should this use locale?
        return instance.equals(instance.toString().toLowerCase());
    }
}
