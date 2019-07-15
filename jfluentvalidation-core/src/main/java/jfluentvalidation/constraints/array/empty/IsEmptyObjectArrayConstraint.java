package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyObjectArrayConstraint<T, E> extends AbstractConstraint<T, E[]> {

    public IsEmptyObjectArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, E[]> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().length == 0;
    }
}
