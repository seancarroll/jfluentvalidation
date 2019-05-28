package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyFloatArrayConstraint<T> implements Constraint<T, float[]> {

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
