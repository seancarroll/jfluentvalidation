package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

public class IsSubstringOfConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence sequence;

    public IsSubstringOfConstraint(CharSequence sequence) {
        this.sequence = Ensure.notNull(sequence);
    }

    // TODO: comparison strategy
    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {

//        assertNotNull(info, actual);
//        checkNotNull(sequence, "Expecting CharSequence not to be null");
//        if (stringContains(sequence.toString(), actual.toString())) return;

        validationContext.getPropertyValue().toString().contains(sequence);

        return false;
    }
}
