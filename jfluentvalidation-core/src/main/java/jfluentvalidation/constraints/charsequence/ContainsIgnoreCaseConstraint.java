package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

public class ContainsIgnoreCaseConstraint implements Constraint<CharSequence> {

    private final CharSequence charSequence;

    public ContainsIgnoreCaseConstraint(CharSequence charSequence) {
        this.charSequence = Ensure.notNull(charSequence);
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return instance.toString().toLowerCase().contains(charSequence.toString().toLowerCase());
    }
}
