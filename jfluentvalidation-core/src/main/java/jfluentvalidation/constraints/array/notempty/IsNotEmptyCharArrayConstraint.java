package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyCharArrayConstraint<T> extends AbstractConstraint<T, char[]> {

    public IsNotEmptyCharArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        return MoreArrays.isArrayNotEmpty(context.getPropertyValue());
    }
}
