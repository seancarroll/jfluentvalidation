package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyObjectArrayConstraint<T> implements Constraint<T, Object[]> {

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
