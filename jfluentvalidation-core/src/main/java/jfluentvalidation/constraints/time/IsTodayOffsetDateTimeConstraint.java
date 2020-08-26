package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.time.Clock;
import java.time.OffsetDateTime;

import static jfluentvalidation.common.Dates.isSameDay;

/**
 * Verifies that the actual {@code OffsetDateTime} is today, matching current year, month and day.
 *
 * @param <T>  type of instance to validate.
 */
public class IsTodayOffsetDateTimeConstraint<T> extends AbstractConstraint<T, OffsetDateTime> {

    private final Clock clock;

    public IsTodayOffsetDateTimeConstraint(Clock clock) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.clock = Ensure.notNull(clock);
    }

    @Override
    public boolean isValid(ConstraintContext<T, OffsetDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return isSameDay(context.getPropertyValue(), OffsetDateTime.now(clock));
    }
}
