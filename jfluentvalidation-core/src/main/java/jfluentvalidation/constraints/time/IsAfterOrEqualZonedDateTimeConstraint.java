package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;

public class IsAfterOrEqualZonedDateTimeConstraint<T> implements Constraint<T, ZonedDateTime> {

    private final ZonedDateTime other;

    public IsAfterOrEqualZonedDateTimeConstraint(ZonedDateTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        return !context.getPropertyValue().isBefore(other);
    }
}
