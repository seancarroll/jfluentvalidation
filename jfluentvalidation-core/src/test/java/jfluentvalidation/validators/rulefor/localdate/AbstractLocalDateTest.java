package jfluentvalidation.validators.rulefor.localdate;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.LocalDate;
import java.time.ZonedDateTime;

abstract class AbstractLocalDateTest {

    protected final ClockProvider clockProvider;
    protected final LocalDate REFERENCE;
    protected final LocalDate BEFORE;
    protected final LocalDate AFTER;

    AbstractLocalDateTest(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        REFERENCE = LocalDate.now(clockProvider.getClock());
        BEFORE = REFERENCE.minusDays(1);
        AFTER = REFERENCE.plusDays(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
