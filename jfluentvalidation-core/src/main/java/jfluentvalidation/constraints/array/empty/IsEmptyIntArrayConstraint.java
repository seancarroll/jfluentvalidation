package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyIntArrayConstraint<T> extends AbstractConstraint<T, int[]> {

    public IsEmptyIntArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
