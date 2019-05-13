package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyDoubleArrayConstraint<T> implements Constraint<T, double[]> {

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        return Arrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
