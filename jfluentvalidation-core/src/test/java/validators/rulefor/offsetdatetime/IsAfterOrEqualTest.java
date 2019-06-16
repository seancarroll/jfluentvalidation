package validators.rulefor.offsetdatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterOrEqualTest {

    private static final OffsetDateTime ACTUAL = OffsetDateTime.of(2019, 6, 15, 0, 0, 0, 0, ZoneOffset.UTC);
    private static final OffsetDateTime BEFORE = OffsetDateTime.of(2019, 6, 14, 0, 0, 0, 0, ZoneOffset.UTC);
    private static final OffsetDateTime AFTER = OffsetDateTime.of(2019, 6, 16, 0, 0, 0, 0, ZoneOffset.UTC);

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfterOrEqual(OffsetDateTime.now());

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfterOrEqual(AFTER);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfterOrEqual(ACTUAL);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Target t = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForOffsetDateTime(Target::getDateTime).isAfterOrEqual(BEFORE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForOffsetDateTime(Target::getDateTime).isAfterOrEqual(null));
    }

}
