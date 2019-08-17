package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyLongArrayConstraint<T> extends AbstractConstraint<T, long[]> {

    public IsNotEmptyLongArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
