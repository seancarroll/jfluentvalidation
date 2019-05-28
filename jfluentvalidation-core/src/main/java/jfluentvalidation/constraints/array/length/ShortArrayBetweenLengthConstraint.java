package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class ShortArrayBetweenLengthConstraint<T> implements Constraint<T, short[]> {

    private final int min;
    private final int max;

    public ShortArrayBetweenLengthConstraint(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.between(len, min, max);
    }
}
