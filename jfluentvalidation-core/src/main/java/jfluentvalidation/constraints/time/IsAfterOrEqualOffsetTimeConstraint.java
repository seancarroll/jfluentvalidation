package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.OffsetTime;

public class IsAfterOrEqualOffsetTimeConstraint<T> implements Constraint<T, OffsetTime> {

    private final OffsetTime other;

    public IsAfterOrEqualOffsetTimeConstraint(OffsetTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetTime> context) {
        return !context.getPropertyValue().isBefore(other);
    }
}
