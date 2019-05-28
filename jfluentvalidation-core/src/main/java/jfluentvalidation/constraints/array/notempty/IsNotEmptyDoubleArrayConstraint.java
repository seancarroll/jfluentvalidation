package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyDoubleArrayConstraint<T> implements Constraint<T, double[]> {

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
