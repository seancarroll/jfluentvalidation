package jfluentvalidation.validators.rulefor.zoneddatetime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.ZonedDateTime;

abstract class AbstractZonedDateTime {

    protected final ClockProvider clockProvider;
    protected final ZonedDateTime reference;
    protected final ZonedDateTime before;
    protected final ZonedDateTime after;

    AbstractZonedDateTime(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        reference = ZonedDateTime.now(clockProvider.getClock());
        before = reference.minusDays(1);
        after = reference.plusDays(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
