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

        PersonValidator validator = new PersonValidator();

        List<ValidationFailure> validationFailures = validator.validate(m);

        assertEquals(1, validationFailures.size());
    }

    private class PersonValidator extends AbstractValidator<Person> {

        protected PersonValidator() {
            ruleForString(p -> p.getName()).isEmpty().startsWith("s").length(0, 4);
            ruleForString(p -> p.getAddress()).isNotNull();
            ruleForInteger(p -> p.getAge()).isPositive();
            ruleForBoolean(p -> p.isMarried()).isFalse();
            ruleForZonedDateTime(p -> p.getSignedIn()).isAfter(ZonedDateTime.now().minusDays(1));
            ruleForMap(p -> p.getPets()).isEmpty();
            ruleForIterable(p -> p.getChilren()).isNotNull().isNotEmpty();
        }
    }

}
