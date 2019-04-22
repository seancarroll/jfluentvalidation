package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;

public class IsAfterZonedDateTimeConstraint<T> implements Constraint<T, ZonedDateTime> {

    private final ZonedDateTime other;

    public IsAfterZonedDateTimeConstraint(ZonedDateTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        return context.getPropertyValue().isAfter(other);
    }
}
