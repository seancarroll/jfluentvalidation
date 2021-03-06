package jfluentvalidation.validators.rulefor.dates;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

abstract class AbstractDateTest {

    /**
     * serves as the reference for {@code now}
     */
    protected final ClockProvider clockProvider;
    protected final Date reference;
    protected final Date before;
    protected final Date after;

    AbstractDateTest(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        Instant instant = clockProvider.getClock().instant();
        reference = Date.from(dateTime.toInstant());
        before = Date.from(instant.minus(Duration.ofDays(1)));
        after = Date.from(instant.plus(Duration.ofDays(1)));
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }
}
