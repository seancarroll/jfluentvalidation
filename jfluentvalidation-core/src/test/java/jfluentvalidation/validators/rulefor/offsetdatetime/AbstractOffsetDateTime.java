package jfluentvalidation.validators.rulefor.offsetdatetime;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

class AbstractOffsetDateTime {

    protected final ClockProvider clockProvider;
    protected final OffsetDateTime REFERENCE;
    protected final OffsetDateTime BEFORE;
    protected final OffsetDateTime AFTER;

    AbstractOffsetDateTime(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        REFERENCE = OffsetDateTime.now(clockProvider.getClock());
        BEFORE = REFERENCE.minusDays(1);
        AFTER = REFERENCE.plusDays(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }
}
