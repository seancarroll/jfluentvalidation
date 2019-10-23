package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>  type of instance to validate.
 */
public class IsSubstringOfConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final CharSequence sequence;

    public IsSubstringOfConstraint(CharSequence sequence) {
        super(DefaultMessages.CHARSEQUENCE_IS_SUBSTRING_OF);
        this.sequence = Ensure.notNull(sequence);
    }

    // TODO: comparison strategy? Something like insensitive the following for case insensitive?
    // Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE).matcher(s1).find();
    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return sequence.toString().contains(context.getPropertyValue());
    }

    @Override
    public void addParametersToContext(RuleContext<T, A> context) {
        context.getMessageContext().appendArgument("sequence", sequence);
    }
}
