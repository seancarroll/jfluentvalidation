package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyObjectArrayConstraint<T> implements Constraint<T, Object[]> {

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
