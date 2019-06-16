package validators.rulefor.localtime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterLocalTimeTest {

    private static final LocalTime ACTUAL = LocalTime.of(2, 0, 0, 0);
    private static final LocalTime BEFORE = LocalTime.of(1, 0, 0, 0);
    private static final LocalTime AFTER = LocalTime.of(3, 0, 0, 0);

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        TestTarget t = new TestTarget(null);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfter(LocalTime.now());

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        TestTarget t = new TestTarget(ACTUAL);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfter(AFTER);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        TestTarget t = new TestTarget(ACTUAL);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfter(ACTUAL);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        TestTarget t = new TestTarget(ACTUAL);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfter(BEFORE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalTime(TestTarget::getTime).isAfter(null));
    }
}
