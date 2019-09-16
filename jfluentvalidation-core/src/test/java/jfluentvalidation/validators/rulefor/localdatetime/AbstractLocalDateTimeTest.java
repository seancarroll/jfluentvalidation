package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

abstract class AbstractLocalDateTimeTest {

    protected final ClockProvider clockProvider;
    protected final LocalDateTime reference;
    protected final LocalDateTime before;
    protected final LocalDateTime after;

    AbstractLocalDateTimeTest(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        reference = LocalDateTime.now(clockProvider.getClock());
        before = reference.minusDays(1);
        after = reference.plusDays(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
