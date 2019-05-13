package jfluentvalidation.constraints.array;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class HasSizeBetweenConstraint<T, A> implements Constraint<T, A[]> {

//    * @param lowerBoundary the lower boundary compared to which actual size should be greater than or equal to.
//    * @param higherBoundary the higher boundary compared to which actual size should be less than or equal to.

    private final int min;
    private final int max;

    public HasSizeBetweenConstraint(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, A[]> context) {
        return false;
    }
}
