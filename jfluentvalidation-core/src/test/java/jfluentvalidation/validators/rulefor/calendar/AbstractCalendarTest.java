package jfluentvalidation.validators.rulefor.calendar;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.ZonedDateTime;
import java.util.Calendar;

import static jfluentvalidation.common.Dates.calendarFromClock;

public abstract class AbstractCalendarTest {

    protected final ClockProvider clockProvider;
    protected final Calendar reference;
    protected final Calendar before;
    protected final Calendar after;

    AbstractCalendarTest(ZonedDateTime dateTime) {
        this.clockProvider =  new FixedClockProvider(dateTime);
        reference = calendarFromClock(clockProvider.getClock());

        before = (Calendar) reference.clone();
        before.add(Calendar.DATE, -1);

        after = Calendar.getInstance();
        after.add(Calendar.DATE, 1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
