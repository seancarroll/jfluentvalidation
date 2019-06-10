package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyLongArrayConstraint<T> extends AbstractConstraint<T, long[]> {

    public IsEmptyLongArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
