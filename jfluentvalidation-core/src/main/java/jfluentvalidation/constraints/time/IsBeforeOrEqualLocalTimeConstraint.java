package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalTime;

public class IsBeforeOrEqualLocalTimeConstraint<T> implements Constraint<T, LocalTime> {

    private final LocalTime other;

    public IsBeforeOrEqualLocalTimeConstraint(LocalTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalTime> context) {
        return !context.getPropertyValue().isAfter(other);
    }
}
