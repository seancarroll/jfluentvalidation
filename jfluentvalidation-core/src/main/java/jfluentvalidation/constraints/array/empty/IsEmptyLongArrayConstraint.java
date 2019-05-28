package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyLongArrayConstraint<T> implements Constraint<T, long[]> {

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
