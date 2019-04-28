package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.OffsetTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterOffsetTimeConstraint<T> implements Constraint<T, OffsetTime> {

    private final OffsetTime other;

    public IsAfterOffsetTimeConstraint(OffsetTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetTime> context) {
        return context.getPropertyValue().isAfter(other);
    }
}
