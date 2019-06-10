package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated ends with the given suffix.
 *
 * @param <T>  type of instance to validate.
 */
public class EndsWithConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final CharSequence suffix;

    public EndsWithConstraint(CharSequence suffix) {
        super(DefaultMessages.CHARSEQUENCE_ENDS_WITH);
        this.suffix = Ensure.notNull(suffix);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().toString().endsWith(suffix.toString());
    }
}
