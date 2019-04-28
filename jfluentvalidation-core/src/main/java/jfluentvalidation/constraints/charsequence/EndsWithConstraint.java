package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated ends with the given suffix.
 * @param <T> type of instance to validate
 */
public class EndsWithConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence suffix;

    public EndsWithConstraint(CharSequence suffix) {
        this.suffix = Ensure.notNull(suffix);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().toString().endsWith(suffix.toString());
    }
}
