package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.Clock;
import java.time.LocalDateTime;

import static jfluentvalidation.common.Dates.isSameDay;

/**
 * Verifies that the actual {@code LocalDateTime} is today, matching current year, month and day.
 *
 * @param <T>  type of instance to validate.
 */
public class IsTodayLocalDateTimeConstraint<T> extends AbstractConstraint<T, LocalDateTime> {

    private final Clock clock;

    public IsTodayLocalDateTimeConstraint(Clock clock) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.clock = Ensure.notNull(clock);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return isSameDay(context.getPropertyValue(), LocalDateTime.now(clock));
    }
}
