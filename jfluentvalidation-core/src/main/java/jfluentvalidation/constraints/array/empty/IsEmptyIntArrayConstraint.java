package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyIntArrayConstraint<T> implements Constraint<T, int[]> {

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
