package validators.rulefor.localtime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterLocalTimeTest {

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
        TestTarget t = new TestTarget(LocalTime.now());

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfter(LocalTime.now().plusHours(1));

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        LocalTime time = LocalTime.now();
        TestTarget t = new TestTarget(time);

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfter(time);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        TestTarget t = new TestTarget(LocalTime.now());

        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        validator.ruleForLocalTime(TestTarget::getTime).isAfter(LocalTime.now().minusHours(1));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<TestTarget> validator = new DefaultValidator<>(TestTarget.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalTime(TestTarget::getTime).isAfter(null));
    }
}
