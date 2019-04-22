package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

// TODO: length constraint where the users specify inclusive/exclusive boundaries
/**
 * Check that the length of the given {@code CharSequence} being validated is between the given lower and higher boundaries (inclusive).
 */
public class HasLengthBetweenConstraint<T> implements Constraint<T, CharSequence> {

    private final int min;
    private final int max;

    public HasLengthBetweenConstraint(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        int len = validationContext.getPropertyValue().length();
        return len >= min && len <= max;
    }
}
