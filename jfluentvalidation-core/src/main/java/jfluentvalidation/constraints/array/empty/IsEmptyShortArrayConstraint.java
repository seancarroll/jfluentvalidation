package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyShortArrayConstraint<T> implements Constraint<T, short[]> {

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
