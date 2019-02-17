package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

public class EndsWithConstraint implements Constraint<CharSequence> {

    private final CharSequence suffix;

    public EndsWithConstraint(CharSequence suffix) {
        this.suffix = Ensure.notNull(suffix);
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.toString().endsWith(suffix.toString());
    }
}
