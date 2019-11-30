package jfluentvalidation.validators.rulefor.iterables;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForEachTest {

    @Test
    void foreachSuccess() {
        Target t = new Target(Arrays.asList("sean", "shawn"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).forEach(startsWith("s"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void foreachFailure() {
        Target t = new Target(Arrays.asList("sean", "shawn"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).forEach(startsWith("a"));

        ValidationResult validationResult = validator.validate(t);

        assertEquals(2, validationResult.getViolations().size());
    }

    @Test
    void forEachWithPredicate() {
        Target t = new Target(Arrays.asList("sean", "bob", "shaun", "shawn", "lou"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        validator.ruleForIterable(Target::getValue).forEach(s -> s.length() > 3, startsWith("s"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void forEachWithPredicateFailure() {
        Target t = new Target(Arrays.asList("sean", "bob", "shaun", "shawn", "lou"));

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForIterable(Target::getValue).forEach(s -> s.length() > 2, startsWith("s"));

        ValidationResult validationResult = validator.validate(t);

        assertEquals(2, validationResult.getViolations().size());
    }

}
