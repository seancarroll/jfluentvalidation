package jfluentvalidation.validators.rulefor.offsettime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

abstract class AbstractOffsetTime {

    protected final ClockProvider clockProvider;
    protected final OffsetTime REFERENCE;
    protected final OffsetTime BEFORE;
    protected final OffsetTime AFTER;

    AbstractOffsetTime(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        REFERENCE = OffsetTime.now(clockProvider.getClock());
        BEFORE = REFERENCE.minusHours(1);
        AFTER = REFERENCE.plusHours(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
