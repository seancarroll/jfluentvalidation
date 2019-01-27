package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation
public class HasSameLengthAsConstraint implements Constraint<CharSequence> {

    private final CharSequence other;

    public HasSameLengthAsConstraint(CharSequence other) {
        this.other = other;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return instance.length() == other.length();
    }
}
