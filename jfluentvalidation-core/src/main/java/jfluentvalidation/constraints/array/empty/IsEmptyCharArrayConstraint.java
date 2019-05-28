package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyCharArrayConstraint<T> implements Constraint<T, char[]> {

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
