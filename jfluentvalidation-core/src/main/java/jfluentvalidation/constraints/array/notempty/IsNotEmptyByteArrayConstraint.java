package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyByteArrayConstraint<T> implements Constraint<T, byte[]> {

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
