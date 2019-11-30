package jfluentvalidation.validators.rulefor.offsetdatetime;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInTheFutureOffsetDateTimeTest extends AbstractOffsetDateTime {

    IsInTheFutureOffsetDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 8, 0, 0, 0,
            TZ_CHICAGO));
    }

    // TODO: test temporal tolerance

    @Test
    void shouldNotReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(after);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInTheFuture();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInTheFuture();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsThePresent() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInThePast();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsInThePast() {
        Target t = new Target(before);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForOffsetDateTime(Target::getDateTime).isInTheFuture();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
