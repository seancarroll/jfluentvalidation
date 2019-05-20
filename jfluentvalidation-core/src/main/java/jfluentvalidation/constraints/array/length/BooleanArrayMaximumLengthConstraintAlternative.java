package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class BooleanArrayMaximumLengthConstraintAlternative<T> implements Constraint<T, boolean[]> {

    private final int max;

    public BooleanArrayMaximumLengthConstraintAlternative(int max) {
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
