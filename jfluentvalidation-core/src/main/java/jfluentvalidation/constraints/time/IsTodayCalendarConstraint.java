package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.time.Clock;
import java.util.Calendar;

import static jfluentvalidation.common.Dates.calendarFromClock;
import static jfluentvalidation.common.Dates.isSameDay;

/**
 * Verifies that the actual {@code Calendar} is today, matching current year, month and day.
 *
 * @param <T>  type of instance to validate.
 */
public class IsTodayCalendarConstraint<T> extends AbstractConstraint<T, Calendar> {

    private final Clock clock;

    public IsTodayCalendarConstraint(Clock clock) {
        super(DefaultMessages.TIME_IS_TODAY);
        this.clock = Ensure.notNull(clock);
    }

    @Override
    public boolean isValid(ConstraintContext<T, Calendar> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return isSameDay(context.getPropertyValue(), calendarFromClock(clock));
    }

}
