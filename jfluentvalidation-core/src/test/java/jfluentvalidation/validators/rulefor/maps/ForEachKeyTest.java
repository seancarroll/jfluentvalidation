package jfluentvalidation.validators.rulefor.maps;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.isLowerCase;
import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.isUpperCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForEachKeyTest {

    @Test
    void success() {
        Target t = new Target(new HashMap<String, String>() {{
            put("foo", "bar");
            put("hello", "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).forEachKey(isLowerCase());

        List<ValidationFailure> failures = validator.validate(t);

        assertTrue(failures.isEmpty());
    }

    @Test
    void failure() {
        Target t = new Target(new HashMap<String, String>() {{
            put("foo", "bar");
            put("hello", "world");
        }});

        DefaultValidator<Target> validator = new DefaultValidator<>(Target.class);
        validator.ruleForMap(Target::getMap).forEachKey(isUpperCase());

        List<ValidationFailure> failures = validator.validate(t);

        System.out.println(failures);
        assertEquals(2, failures.size());
    }


}
