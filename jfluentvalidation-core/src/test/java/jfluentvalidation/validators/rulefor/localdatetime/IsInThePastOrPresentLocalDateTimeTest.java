package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static jfluentvalidation.TimeZones.TZ_CHICAGO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInThePastOrPresentLocalDateTimeTest extends AbstractLocalDateTimeTest {

    IsInThePastOrPresentLocalDateTimeTest() {
        super(ZonedDateTime.of(
            2019, 6, 15, 0, 0, 0, 0,
            TZ_CHICAGO));
    }

    // TODO: test temporal tolerance

    @Test
    void shouldNotReturnFailureWhenActualIsInThePast() {
        Target t = new Target(before);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isInThePastOrPresent();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsThePresent() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isInThePastOrPresent();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isInThePastOrPresent();

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void shouldReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(after);

        DefaultValidator<Target> validator = getValidator();
        validator.ruleForLocalDateTime(Target::getDateTime).isInThePastOrPresent();

        ValidationResult validationResult = validator.validate(t);

        assertFalse(validationResult.isValid());
    }

}
