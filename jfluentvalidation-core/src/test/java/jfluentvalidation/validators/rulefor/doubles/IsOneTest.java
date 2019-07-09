package jfluentvalidation.validators.rulefor.doubles;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsOneTest {

    @Test
    void shouldNotReturnFailureWhenActualIsOne() {
        Target t = new Target(1d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isOne();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isOne();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotOne() {
        Target t = new Target(2d);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForDouble(Target::getNumber).isOne();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }
}
