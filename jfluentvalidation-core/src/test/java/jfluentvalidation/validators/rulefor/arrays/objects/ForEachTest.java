package jfluentvalidation.validators.rulefor.arrays.objects;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.List;
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

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void foreachFailure() {
        Target t = new Target(new String[] {"sean", "shawn"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).forEach(startsWith("a"));

        List<ValidationFailure> failures = validator.validate(t);

        assertEquals(2, failures.size());
    }

    @Test
    void forEachWithPredicate() {
        Target t = new Target(new String[] {"sean", "bob", "shaun", "shawn", "lou"});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);

        validator.ruleForObjectArray(Target::getValue).forEach(s -> s.length() > 3, startsWith("s"));

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void forEachWithPredicateFailure() {
        Target t = new Target(new String[] {"sean", "bob", "shaun", "shawn", "lou"});

        // TODO: this sucks :(
        Predicate<Object> stringGreaterThan3Predicate = s -> ((String) s).length() > 2;
        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForObjectArray(Target::getValue).forEach(stringGreaterThan3Predicate, startsWith("s"));

        List<ValidationFailure> failures = validator.validate(t);

        assertEquals(2, failures.size());
    }


}
