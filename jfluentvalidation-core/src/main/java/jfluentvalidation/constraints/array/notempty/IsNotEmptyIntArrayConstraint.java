package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyIntArrayConstraint<T> extends AbstractConstraint<T, int[]> {

    public IsNotEmptyIntArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
