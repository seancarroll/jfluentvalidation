package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks that the given {@code CharSequence} being validated equals the given sequence, ignoring case.
 *
 * @param <T>  type of instance to validate.
 */
public class IsEqualToIgnoringCaseConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence other;

    public IsEqualToIgnoringCaseConstraint(CharSequence other) {
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().toString().equalsIgnoreCase(other.toString());
    }
}
