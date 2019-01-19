package jfluentvalidation.constraints;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.AbstractValidator;
import org.junit.jupiter.api.Test;
import validators.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringConstraintTest {

    @Test
    void test() {
        Person m = new Person("sean", 32, "");

        PersonValidator validator = new PersonValidator();
        // TODO: how to get property/field name?
        // serialized lambdas dont provide an actual impl method name with anonymous lambdas
        // bytebuddy proxy method interceptors do seem to work
        validator.ruleFor(p -> p.getName()).isEmpty().startsWith("s").length(0, 4);
        // validator.ruleFor(m.getName()); //.isNotEmpty();
        List<ValidationFailure> validationFailures = validator.validate(m);

        assertEquals(1, validationFailures.size());

        // TODO: check what hibernate returns
        // how to override/provide error code or error message
        // Set<ConstraintViolation<Person>> constraints = validator.validate();
    }

    private class PersonValidator extends AbstractValidator<Person> {

        protected PersonValidator() {
            // maybe we do something like this?
            // ruleFor(source::getName);
        }
    }
}
