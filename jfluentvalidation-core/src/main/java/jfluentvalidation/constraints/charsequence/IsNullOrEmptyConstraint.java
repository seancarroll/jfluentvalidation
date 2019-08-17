package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNullOrEmptyConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    public IsNullOrEmptyConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().toString().isEmpty();
    }
}
