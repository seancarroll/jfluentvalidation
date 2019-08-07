package jfluentvalidation.validators.rulefor.localdate;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsInTheFutureTest {

    private static final LocalDate PAST = LocalDate.now().minusDays(1);
    private static final LocalDate FUTURE = LocalDate.now().plusDays(1);

    // TODO: test for same  need to implement clock.

    @Test
    void shouldNotReturnFailureWhenActualIsInTheFuture() {
        Target t = new Target(FUTURE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDate(Target::getDate).isInTheFuture();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDate(Target::getDate).isInTheFuture();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsInThePast() {
        Target t = new Target(PAST);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLocalDate(Target::getDate).isInTheFuture();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
