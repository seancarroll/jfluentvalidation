package validators.rulefor.localtime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterOrEqualTest {

    private static final LocalTime REFERENCE = LocalTime.of(2, 0, 0, 0);
    private static final LocalTime BEFORE = LocalTime.of(1, 0, 0, 0);
    private static final LocalTime AFTER = LocalTime.of(3, 0, 0, 0);

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        TestTarget t = new TestTarget(null);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfterOrEqual(LocalTime.now());

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        TestTarget t = new TestTarget(REFERENCE);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfterOrEqual(AFTER);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        TestTarget t = new TestTarget(REFERENCE);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfterOrEqual(REFERENCE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        TestTarget t = new TestTarget(REFERENCE);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfterOrEqual(BEFORE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalTime(TestTarget::getTime).isAfterOrEqual(null));
    }
}
