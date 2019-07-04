package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.startsWith;
import static org.junit.jupiter.api.Assertions.*;

class ForEachTest {

    @Test
    void foreachSuccess() {
        Target t = new Target(Arrays.asList("sean", "shawn"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).forEach(startsWith("s"));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void foreachFailure() {
        Target t = new Target(Arrays.asList("sean", "shawn"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).forEach(startsWith("a"));

        List<ValidationFailure> failures = validator.validate(t);

        assertEquals(2, failures.size());
    }

    @Test
    void forEachWithPredicate() {
        Target t = new Target(Arrays.asList("sean", "bob", "shaun", "shawn", "lou"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        validator.ruleForIterable(Target::getValue).forEach(s -> s.length() > 3, startsWith("s"));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void forEachWithPredicateFailure() {
        Target t = new Target(Arrays.asList("sean", "bob", "shaun", "shawn", "lou"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        validator.ruleForIterable(Target::getValue).forEach(s -> s.length() > 2, startsWith("s"));

        List<ValidationFailure> failures = validator.validate(t);

        assertEquals(2, failures.size());
    }

}