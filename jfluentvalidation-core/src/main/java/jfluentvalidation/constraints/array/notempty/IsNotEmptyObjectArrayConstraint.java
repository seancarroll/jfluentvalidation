package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyObjectArrayConstraint<T, E> extends AbstractConstraint<T, E[]> {

    public IsNotEmptyObjectArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, E[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
