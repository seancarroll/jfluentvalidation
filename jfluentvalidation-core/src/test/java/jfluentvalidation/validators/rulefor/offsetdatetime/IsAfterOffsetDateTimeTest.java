package jfluentvalidation.validators.rulefor.offsetdatetime;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsAfterOffsetDateTimeTest extends AbstractOffsetDateTime {

    IsAfterOffsetDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 8, 0, 0, 0,
            TZ_CHICAGO));
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(before);

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }


    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(OffsetDateTime.now());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(after);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(reference);

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(null));
    }

}
