package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyFloatArrayConstraint<T> extends AbstractConstraint<T, float[]> {

    public IsEmptyFloatArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
