package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class BooleanArrayMinimumLengthConstraintAlternative<T> implements Constraint<T, boolean[]> {

    private final int min;

    public BooleanArrayMinimumLengthConstraintAlternative(int min) {
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
