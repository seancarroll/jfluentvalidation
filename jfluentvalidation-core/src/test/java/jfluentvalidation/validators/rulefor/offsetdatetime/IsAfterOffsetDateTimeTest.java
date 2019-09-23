package jfluentvalidation.validators.rulefor.offsetdatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

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

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }


    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(OffsetDateTime.now());

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(after);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(reference);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(reference);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForOffsetDateTime(Target::getDateTime).isAfter(null));
    }

}
