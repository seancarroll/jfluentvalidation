package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class EndsWithConstraint implements Constraint<CharSequence> {

    private final CharSequence suffix;

    public EndsWithConstraint(CharSequence suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return false;
    }
}
