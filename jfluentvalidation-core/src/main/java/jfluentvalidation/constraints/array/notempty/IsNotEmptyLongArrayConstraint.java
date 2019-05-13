package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyLongArrayConstraint<T> implements Constraint<T, long[]> {

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        return Arrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
