package jfluentvalidation.validators.rulefor.localtime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.LocalTime;
import java.time.ZonedDateTime;

abstract class AbstractLocalTimeTest {

    protected final ClockProvider clockProvider;
    protected final LocalTime REFERENCE;
    protected final LocalTime BEFORE;
    protected final LocalTime AFTER;

    AbstractLocalTimeTest(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        REFERENCE = LocalTime.now(clockProvider.getClock());
        BEFORE = REFERENCE.minusHours(1);
        AFTER = REFERENCE.plusHours(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }
}
