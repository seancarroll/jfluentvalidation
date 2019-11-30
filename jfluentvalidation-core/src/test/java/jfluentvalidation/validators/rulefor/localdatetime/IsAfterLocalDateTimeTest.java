package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsAfterLocalDateTimeTest extends AbstractLocalDateTimeTest {

    IsAfterLocalDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 0, 0, 0, 0,
            TZ_CHICAGO));
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isAfter(before);

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target p = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isAfter(LocalDateTime.now());

        ValidationResult validationResult = validator.validate(p);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isAfter(after);

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target p = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isAfter(reference);

        ValidationResult validationResult = validator.validate(p);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = getValidator();
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDateTime(Target::getDateTime).isAfter(null));
    }

}
