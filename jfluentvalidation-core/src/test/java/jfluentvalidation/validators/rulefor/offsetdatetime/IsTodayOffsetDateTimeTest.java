package jfluentvalidation.validators.rulefor.offsetdatetime;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsTodayOffsetDateTimeTest extends AbstractOffsetDateTime {

    IsTodayOffsetDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 0, 0, 0, 0,
            TZ_CHICAGO));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsToday() {
        Target t = new Target(OffsetDateTime.now(clockProvider.getClock()));

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isToday();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isToday();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotToday() {
        Target t = new Target(before);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isToday();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
