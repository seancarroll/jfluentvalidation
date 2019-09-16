package jfluentvalidation.validators.rulefor.offsettime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

abstract class AbstractOffsetTime {

    protected final ClockProvider clockProvider;
    protected final OffsetTime reference;
    protected final OffsetTime before;
    protected final OffsetTime after;

    AbstractOffsetTime(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        reference = OffsetTime.now(clockProvider.getClock());
        before = reference.minusHours(1);
        after = reference.plusHours(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
