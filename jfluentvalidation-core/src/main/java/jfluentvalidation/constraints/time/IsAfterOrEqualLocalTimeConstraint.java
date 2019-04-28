package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOrEqualLocalTimeConstraint<T> implements Constraint<T, LocalTime> {

    private final LocalTime other;

    public IsAfterOrEqualLocalTimeConstraint(LocalTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalTime> context) {
        return !context.getPropertyValue().isBefore(other);
    }
}
