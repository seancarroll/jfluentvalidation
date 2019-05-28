package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyCharArrayConstraint<T> implements Constraint<T, char[]> {

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
