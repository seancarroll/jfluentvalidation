package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyShortArrayConstraint<T> implements Constraint<T, short[]> {

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        return Arrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
