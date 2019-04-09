package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;

public class IsBeforeZonedDateTimeConstraint<T> implements Constraint<T, ZonedDateTime> {

    private final ZonedDateTime other;

    public IsBeforeZonedDateTimeConstraint(ZonedDateTime other) {
        this.other = Ensure.notNull(other);
    }
    
    @Override
    public boolean isValid(RuleContext<T, ZonedDateTime> context) {
        return context.getPropertyValue().isBefore(other);
    }
}
