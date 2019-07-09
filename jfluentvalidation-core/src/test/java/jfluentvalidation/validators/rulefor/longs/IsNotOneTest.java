package jfluentvalidation.validators.rulefor.longs;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsNotOneTest {

    @Test
    void shouldNotReturnFailureWhenActualIsNotOne() {
        Target t = new Target(2L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isNotOne();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isNotOne();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsOne() {
        Target t = new Target(1L);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForLong(Target::getNumber).isNotOne();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
