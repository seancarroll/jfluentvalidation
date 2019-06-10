package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyByteArrayConstraint<T> extends AbstractConstraint<T, byte[]> {

    public IsEmptyByteArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
