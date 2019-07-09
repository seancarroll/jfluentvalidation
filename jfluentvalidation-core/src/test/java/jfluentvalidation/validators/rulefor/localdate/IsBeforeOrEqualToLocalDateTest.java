package jfluentvalidation.validators.rulefor.localdate;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsBeforeOrEqualToLocalDateTest {

    private static final LocalDate ACTUAL = LocalDate.of(2019, 6, 15);
    private static final LocalDate BEFORE = LocalDate.of(2019, 6, 14);
    private static final LocalDate AFTER = LocalDate.of(2019, 6, 16);

    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        validator.ruleForLocalDate(Target::getDate).isBeforeOrEqualTo(ACTUAL);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        validator.ruleForLocalDate(Target::getDate).isBeforeOrEqualTo(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target p = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        validator.ruleForLocalDate(Target::getDate).isBeforeOrEqualTo(LocalDate.now());

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Target p = new Target(ACTUAL);

        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        validator.ruleForLocalDate(Target::getDate).isBeforeOrEqualTo(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>( Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDate(Target::getDate).isBeforeOrEqualTo(null));
    }
}
