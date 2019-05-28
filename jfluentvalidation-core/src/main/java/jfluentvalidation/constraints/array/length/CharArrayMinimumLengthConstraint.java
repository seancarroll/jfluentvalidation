package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class CharArrayMinimumLengthConstraint<T> implements Constraint<T, char[]> {

    private final int min;

    public CharArrayMinimumLengthConstraint(int min) {
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
