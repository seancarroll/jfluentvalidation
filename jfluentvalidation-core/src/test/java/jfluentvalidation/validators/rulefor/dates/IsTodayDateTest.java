package jfluentvalidation.validators.rulefor.dates;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsTodayDateTest extends AbstractDateTest {

    IsTodayDateTest() {
        super(ZonedDateTime.of(
            2019, 8, 7, 9, 0, 0, 0,
            TZ_CHICAGO)
        );
    }

    @Test
    void shouldNotReturnFailureWhenActualIsToday() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isToday();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isToday();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotToday() {
        Target t = new Target(before);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForDate(Target::getDate).isToday();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }
}
