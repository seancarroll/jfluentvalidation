package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class IsEqualToIgnoringCaseConstraint implements Constraint<CharSequence> {

    private final CharSequence other;

    public IsEqualToIgnoringCaseConstraint(CharSequence other) {
        this.other = other;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return instance.toString().equalsIgnoreCase(other.toString());
    }
}