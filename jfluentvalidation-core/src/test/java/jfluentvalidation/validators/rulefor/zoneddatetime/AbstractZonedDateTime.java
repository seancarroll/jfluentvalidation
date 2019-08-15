package jfluentvalidation.validators.rulefor.zoneddatetime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.ZonedDateTime;

abstract class AbstractZonedDateTime {

    protected final ClockProvider clockProvider;
    protected final ZonedDateTime REFERENCE;
    protected final ZonedDateTime BEFORE;
    protected final ZonedDateTime AFTER;

    AbstractZonedDateTime(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        REFERENCE = ZonedDateTime.now(clockProvider.getClock());
        BEFORE = REFERENCE.minusDays(1);
        AFTER = REFERENCE.plusDays(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
