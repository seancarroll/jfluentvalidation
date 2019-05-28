package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyByteArrayConstraint<T> implements Constraint<T, byte[]> {

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
