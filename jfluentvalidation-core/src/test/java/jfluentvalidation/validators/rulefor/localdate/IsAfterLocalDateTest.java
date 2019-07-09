package jfluentvalidation.validators.rulefor.localdate;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterLocalDateTest {

    private static final LocalDate ACTUAL = LocalDate.of(2019, 6, 15);
    private static final LocalDate BEFORE = LocalDate.of(2019, 6, 14);
    private static final LocalDate AFTER = LocalDate.of(2019, 6, 16);

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDate(Target::getDate).isAfter(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target p = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDate(Target::getDate).isAfter(LocalDate.now());

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDate(Target::getDate).isAfter(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDate(Target::getDate).isAfter(ACTUAL);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDate(Target::getDate).isAfter(null));
    }
}
