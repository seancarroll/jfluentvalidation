package jfluentvalidation.validators.rulefor.shorts;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static jfluentvalidation.validators.rulefor.shorts.Constants.FIVE;
import static jfluentvalidation.validators.rulefor.shorts.Constants.TEN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsLessThanOrEqualTest {

    @Test
    void shouldNotReturnFailureWhenActualIsLessThanGiven() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isLessThanOrEqualTo(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualEqualsGiven() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isLessThanOrEqualTo(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isLessThanOrEqualTo(TEN);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualGreaterThanGiven() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForShort(Target::getNumber).isLessThanOrEqualTo(FIVE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
