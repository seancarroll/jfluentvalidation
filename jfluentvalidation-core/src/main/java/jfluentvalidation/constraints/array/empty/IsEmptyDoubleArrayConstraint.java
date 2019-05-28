package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>
 */
public class IsEmptyDoubleArrayConstraint<T> implements Constraint<T, double[]> {

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        return context.getPropertyValue().length == 0;
    }
}
