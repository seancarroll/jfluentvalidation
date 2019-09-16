package jfluentvalidation.validators.rulefor.localtime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.LocalTime;
import java.time.ZonedDateTime;

abstract class AbstractLocalTimeTest {

    protected final ClockProvider clockProvider;
    protected final LocalTime reference;
    protected final LocalTime before;
    protected final LocalTime after;

    AbstractLocalTimeTest(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        reference = LocalTime.now(clockProvider.getClock());
        before = reference.minusHours(1);
        after = reference.plusHours(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }
}
