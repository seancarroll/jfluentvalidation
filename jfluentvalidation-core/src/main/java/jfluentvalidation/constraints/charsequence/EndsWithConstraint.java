package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class EndsWithConstraint implements Constraint<CharSequence> {

    private final CharSequence suffix;

    public EndsWithConstraint(CharSequence suffix) {
        // TODO: null check
        this.suffix = suffix;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return instance.toString().endsWith(suffix.toString());
    }
}
