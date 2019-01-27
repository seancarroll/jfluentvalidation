package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class ContainsIgnoreCaseConstraint implements Constraint<CharSequence> {

    private final CharSequence charSequence;

    public ContainsIgnoreCaseConstraint(CharSequence charSequence) {
        this.charSequence = charSequence;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return false;
    }
}
