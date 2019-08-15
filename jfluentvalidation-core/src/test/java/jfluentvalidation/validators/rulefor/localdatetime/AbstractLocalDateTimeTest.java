package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

abstract class AbstractLocalDateTimeTest {

    protected final ClockProvider clockProvider;
    protected final LocalDateTime REFERENCE;
    protected final LocalDateTime BEFORE;
    protected final LocalDateTime AFTER;

    AbstractLocalDateTimeTest(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        REFERENCE = LocalDateTime.now(clockProvider.getClock());
        BEFORE = REFERENCE.minusDays(1);
        AFTER = REFERENCE.plusDays(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
