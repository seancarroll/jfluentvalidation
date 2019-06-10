package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyDoubleArrayConstraint<T> extends AbstractConstraint<T, double[]> {

    public IsEmptyDoubleArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
