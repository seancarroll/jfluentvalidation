package jfluentvalidation.constraints.array;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class HasSizeConstraint<T, A> implements Constraint<T, A[]> {

    private final int expected;

    public HasSizeConstraint(int expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, A[]> context) {
        return context.getPropertyValue().length == expected;
    }
}

