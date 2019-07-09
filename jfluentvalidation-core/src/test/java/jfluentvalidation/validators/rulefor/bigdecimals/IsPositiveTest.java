package jfluentvalidation.validators.rulefor.bigdecimals;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.ONE;
import static jfluentvalidation.validators.rulefor.bigdecimals.Constants.NEGATIVE_ONE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsPositiveTest {

    @Test
    void shouldNotReturnFailureWhenActualIsPositive() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isPositive();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isPositive();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotPositive() {
        Target t = new Target(NEGATIVE_ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isPositive();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
