package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyBooleanArrayConstraint<T> implements Constraint<T, boolean[]> {

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
