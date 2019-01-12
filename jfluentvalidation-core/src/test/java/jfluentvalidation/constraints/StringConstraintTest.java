package jfluentvalidation.constraints;

import jfluentvalidation.validators.AbstractValidator;
import org.junit.jupiter.api.Test;
import validators.Person;

class StringConstraintTest {

    @Test
    void isEmpty() {
        Person m = new Person("sean", 32, "");

        PersonValidator validator = new PersonValidator(m);
        // TODO: how to get property/field name?
        // serialized lambdas dont provide an actual impl method name with anonymous lambdas
        // bytebuddy proxy method interceptors do seem to work
        validator.ruleFor(p -> p.getName()).isEmpty();
        // validator.ruleFor(m.getName()); //.isNotEmpty();
        validator.validate(m);

        // TODO: check what hibernate returns
        // how to override/provide error code or error message
        // Set<ConstraintViolation<Person>> constraints = validator.validate();
    }

    private class PersonValidator extends AbstractValidator<Person> {

        protected PersonValidator(Person source) {
            super(source);

            // maybe we do something like this?
            // ruleFor(source::getName);
        }
    }
}
