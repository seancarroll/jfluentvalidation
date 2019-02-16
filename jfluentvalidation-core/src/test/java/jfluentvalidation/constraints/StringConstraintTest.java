package jfluentvalidation.constraints;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.AbstractValidator;
import org.junit.jupiter.api.Test;
import validators.Person;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringConstraintTest {

    @Test
    void test() {
        Person m = new Person("sean", 32, "");

        // TODO: better way than creating a validator like this?
        // the fluent validator approach might be a good way to do it
        // although it might be nice to be able to create a validator without having a custom validator class
        // that we need to instantiate
        PersonValidator validator = new PersonValidator();

        validator.ruleForString(p -> p.getName()).isEmpty().startsWith("s").length(0, 4);
        validator.ruleForString(p -> p.getAddress()).isNotNull();
        validator.ruleForInteger(p -> p.getAge()).isPositive();
        validator.ruleForBoolean(p -> p.isMarried()).isFalse();
        validator.ruleForZonedDateTime(p -> p.getSignedIn()).isAfter(ZonedDateTime.now().minusDays(1));

        List<ValidationFailure> validationFailures = validator.validate(m);

        assertEquals(1, validationFailures.size());
    }

    private class PersonValidator extends AbstractValidator<Person> {

        protected PersonValidator() {
            // maybe we do something like this?
            // ruleFor(source::getName);
        }
    }
}
