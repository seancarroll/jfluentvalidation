package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationResult;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.isLowerCase;
import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.isUpperCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForEachValueTest {

    @Test
    void success() {
        Target t = new Target(new HashMap<String, String>() {{
            put("foo", "bar");
            put("hello", "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).forEachValue(isLowerCase());

        ValidationResult validationResult = validator.validate(t);

        assertTrue(validationResult.isValid());
    }

    @Test
    void failure() {
        Target t = new Target(new HashMap<String, String>() {{
            put("foo", "bar");
            put("hello", "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).forEachValue(isUpperCase());

        ValidationResult validationResult = validator.validate(t);

        assertEquals(2, validationResult.getViolations().size());
    }
}
