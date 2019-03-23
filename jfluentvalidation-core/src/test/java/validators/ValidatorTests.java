package validators;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.AbstractValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTests {

    @Test
    void noCustomClass() {
        Person person = new Person("sean", 32, "");

        AbstractValidator<Person> validator = new AbstractValidator<>();
        validator.ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(5, 10);
        validator.ruleForInteger(p -> p.getAge()).isPositive();
        validator.ruleForString(Person::getAddress).isEmpty();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }


    @Test
    void customValidator() {
        Person m = new Person("sean", 32, "");
        m.setChilren(Arrays.asList("Shawn", "Sally"));

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
        validator.ruleForMap(p -> p.getPets()).isEmpty();
        validator.ruleForIterable(p -> p.getChilren()).isNotNull().isNotEmpty();


        List<ValidationFailure> validationFailures = validator.validate(m);

        assertEquals(1, validationFailures.size());
    }

    private class PersonValidator extends AbstractValidator<Person> {

        protected PersonValidator() {
            // TODO: how do we think we should implement a way to add constraints for each item in a collection?
            // One idea...
            // ruleForIterable(p -> p.getChilren()).forEach(startsWith("S"));
        }
    }

}
