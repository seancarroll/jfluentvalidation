package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyBooleanArrayConstraint<T> implements Constraint<T, boolean[]> {

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        return Arrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
