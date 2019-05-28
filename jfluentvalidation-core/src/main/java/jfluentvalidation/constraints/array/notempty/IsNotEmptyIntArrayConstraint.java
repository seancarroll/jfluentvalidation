package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyIntArrayConstraint<T> implements Constraint<T, int[]> {

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
