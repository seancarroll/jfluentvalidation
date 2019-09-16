package jfluentvalidation.validators.rulefor.offsetdatetime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

class AbstractOffsetDateTime {

    protected final ClockProvider clockProvider;
    protected final OffsetDateTime reference;
    protected final OffsetDateTime before;
    protected final OffsetDateTime after;

    AbstractOffsetDateTime(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        reference = OffsetDateTime.now(clockProvider.getClock());
        before = reference.minusDays(1);
        after = reference.plusDays(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }
}
