package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyByteArrayConstraint<T> implements Constraint<T, byte[]> {

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        return Arrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
