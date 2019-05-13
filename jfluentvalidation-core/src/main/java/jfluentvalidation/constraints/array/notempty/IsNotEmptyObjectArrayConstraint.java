package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyObjectArrayConstraint<T> implements Constraint<T, Object[]> {

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        return Arrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
