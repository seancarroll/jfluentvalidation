package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsEmptyTest {

    @Test
    void shouldNotReturnFailureWhenActualIsEmpty() {
        Target t = new Target(Collections.emptyList());

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).isEmpty();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualIsNull() {
        Target t = new Target(null);

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).isEmpty();

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotEmpty() {
        Target t = new Target(Collections.singletonList("hello"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).isEmpty();

        List<ValidationFailure> failures = validator.validate(t);

        assertFalse(failures.isEmpty());
    }

}
