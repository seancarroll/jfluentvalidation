package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyFloatArrayConstraint<T> extends AbstractConstraint<T, float[]> {

    public IsNotEmptyFloatArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
