package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

/**
 * Check that the given {@code CharSequence} being validated ends with the given suffix.
 */
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
