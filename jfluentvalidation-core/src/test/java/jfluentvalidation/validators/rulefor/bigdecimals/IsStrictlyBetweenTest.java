package jfluentvalidation.validators.rulefor.bigdecimals;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static jfluentvalidation.validators.rulefor.bigdecimals.Constants.FIVE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsStrictlyBetweenTest {

    @Test
    void shouldThrowExceptionWhenStartIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigDecimal(Target::getNumber).isStrictlyBetween(null, FIVE));
    }

    @Test
    void shouldThrowExceptionWhenEndIsNull() {
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForBigDecimal(Target::getNumber).isStrictlyBetween(ZERO, null));
    }

    @Test
    void shouldNotReturnFailureWhenActualIsStrictlyInRange() {
        Target t = new Target(ONE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isStrictlyBetween(ZERO, FIVE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToStart() {
        Target t = new Target(ZERO);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isStrictlyBetween(ZERO, FIVE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsEqualToEnd() {
        Target t = new Target(FIVE);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isStrictlyBetween(ZERO, FIVE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isStrictlyBetween(ZERO, FIVE);

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsBeforeStart() {
        Target t = new Target(ZERO);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isStrictlyBetween(ONE, FIVE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsAfterEnd() {
        Target t = new Target(TEN);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForBigDecimal(Target::getNumber).isStrictlyBetween(ZERO, FIVE);

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
