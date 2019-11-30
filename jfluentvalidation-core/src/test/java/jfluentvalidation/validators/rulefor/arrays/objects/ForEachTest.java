package jfluentvalidation.validators.rulefor.arrays.objects;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForEachTest {

    @Test
    void foreachSuccess() {
        Target t = new Target(new String[] {"sean", "shawn"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).forEach(startsWith("s"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void foreachFailure() {
        Target t = new Target(new String[] {"sean", "shawn"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).forEach(startsWith("a"));

        ValidationResult validationResult = validator.validate(t);

        assertEquals(2, validationResult.getViolations().size());
    }

    @Test
    void forEachWithPredicate() {
        Target t = new Target(new String[] {"sean", "bob", "shaun", "shawn", "lou"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        validator.ruleForObjectArray(Target::getValue).forEach(s -> s.length() > 3, startsWith("s"));

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void forEachWithPredicateFailure() {
        Target t = new Target(new String[] {"sean", "bob", "shaun", "shawn", "lou"});

        // TODO: this sucks :(
        Predicate<Object> stringGreaterThan3Predicate = s -> ((String) s).length() > 2;
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).forEach(stringGreaterThan3Predicate, startsWith("s"));

        ValidationResult validationResult = validator.validate(t);

        assertEquals(2, validationResult.getViolations().size());
    }


}
