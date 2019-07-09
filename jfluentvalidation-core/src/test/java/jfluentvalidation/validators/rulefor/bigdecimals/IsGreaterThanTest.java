package jfluentvalidation.validators.rulefor.bigdecimals;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.TEN;
import static jfluentvalidation.validators.rulefor.bigdecimals.Constants.FIVE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsGreaterThanTest {

    @Test
    void shouldNotReturnFailureWhenActualIsGreaterThanGiven() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThan(FIVE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThan(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGiven() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThan(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualLessThanGiven() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isGreaterThan(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
