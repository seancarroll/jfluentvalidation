package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyCharArrayConstraint<T> implements Constraint<T, char[]> {

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        return Arrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
