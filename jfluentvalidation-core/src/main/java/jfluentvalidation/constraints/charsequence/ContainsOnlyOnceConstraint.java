package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

// TODO: allow custom comparison strategy

/**
 * Check that the given {@code CharSequence} being validated contains the given sequence only once.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsOnlyOnceConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final CharSequence sequence;

    public ContainsOnlyOnceConstraint(CharSequence sequence) {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_ONLY_ONCE);
        this.sequence = Ensure.notNull(sequence);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        if (Strings.isNull(validationContext.getPropertyValue())) {
            return false;
        }

        String sequenceAsString = sequence.toString();
        String instanceAsString = validationContext.getPropertyValue().toString();

        // TODO: do we want to count all instances and include in message/context
        int count = 0;
        int fromIndex = 0;
        while ((fromIndex = instanceAsString.indexOf(sequenceAsString, fromIndex)) != -1) {
            count++;
            if (count >= 2) {
                return false;
            }
            fromIndex += sequenceAsString.length();
        }

        return count == 1;
    }
}
