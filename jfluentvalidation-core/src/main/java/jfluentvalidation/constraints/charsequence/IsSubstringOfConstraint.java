package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>  type of instance to validate.
 */
public class IsSubstringOfConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final CharSequence sequence;

    public IsSubstringOfConstraint(CharSequence sequence) {
        super(DefaultMessages.CHARSEQUENCE_IS_SUBSTRING_OF);
        this.sequence = Ensure.notNull(sequence);
    }

    // TODO: comparison strategy
    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().toString().contains(sequence);
    }
}
