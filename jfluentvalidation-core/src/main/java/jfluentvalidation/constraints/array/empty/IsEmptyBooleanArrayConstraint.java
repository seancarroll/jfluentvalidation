package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyBooleanArrayConstraint<T> extends AbstractConstraint<T, boolean[]> {

    public IsEmptyBooleanArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length == 0;
    }
}
