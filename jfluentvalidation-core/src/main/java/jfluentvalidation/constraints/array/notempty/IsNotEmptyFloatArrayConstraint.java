package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyFloatArrayConstraint<T> implements Constraint<T, float[]> {

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        return Arrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
