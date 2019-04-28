package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.ZonedDateTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
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
