package jfluentvalidation.validators.rulefor.localdate;

import jfluentvalidation.internal.FixedClockProvider;
import jfluentvalidation.validators.DefaultValidator;
import jfluentvalidation.validators.ValidatorFactory;

import javax.validation.ClockProvider;
import java.time.LocalDate;
import java.time.ZonedDateTime;

abstract class AbstractLocalDateTest {

    protected final ClockProvider clockProvider;
    protected final LocalDate reference;
    protected final LocalDate before;
    protected final LocalDate after;

    AbstractLocalDateTest(ZonedDateTime dateTime) {
        this.clockProvider = new FixedClockProvider(dateTime);

        reference = LocalDate.now(clockProvider.getClock());
        before = reference.minusDays(2);
        after = reference.plusDays(1);
    }

    @SuppressWarnings("unchecked")
    <T> DefaultValidator<T> getValidator() {
        return new ValidatorFactory().withClockProvider(clockProvider).create((Class<T>) Target.class);
    }

}
