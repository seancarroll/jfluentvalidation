package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyLongArrayConstraint<T> implements Constraint<T, long[]> {

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
