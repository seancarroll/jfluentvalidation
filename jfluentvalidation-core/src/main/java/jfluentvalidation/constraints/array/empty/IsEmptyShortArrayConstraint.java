package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyShortArrayConstraint<T> extends AbstractConstraint<T, short[]> {

    public IsEmptyShortArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length == 0;
    }
}
