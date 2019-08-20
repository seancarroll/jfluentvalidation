package jfluentvalidation.validators;

import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Predicate;

import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTests {

    @Test
    void supportLambdaExpression() {
        Person person = new Person("sean", -1, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForInteger(p -> p.getAge()).isPositive();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
        assertEquals("age", validationFailures.get(0).getPropertyName());
    }

    @Test
    void defaultValidator() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotEmpty().startsWith("s").length(5, 10).withMessage("Name must be between 5 and 10 yo");
        validator.ruleForInteger(Person::getAge).isPositive();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }

    @Test
    void staticFactory() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = DefaultValidator.forClass(Person.class);
        validator.ruleForString(Person::getName).isNotEmpty().startsWith("s").length(5, 10);
        validator.ruleForInteger(Person::getAge).isPositive().isBetween(1, 50);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }

    @Test
    void customValidator() {
        Person person = new Person("sean", 32, null);
        person.setChildren(Arrays.asList("Shawn", "Sally"));

        Map<String, String> pets = new HashMap<>();
        pets.put("otis", "dog");
        pets.put("milo", "cat");
        person.setPets(pets);

        PersonValidator validator = new PersonValidator();

        List<ValidationFailure> validationFailures = validator.validate(person);
        System.out.println(validationFailures);
        assertEquals(0, validationFailures.size());
    }

    @Test
    void ageValidator() {
        Person person = new Person("sean", 32, null);

        PersonAgeValidator validator = new PersonAgeValidator();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(0, validationFailures.size());
    }

    @Test
    void ruleSet() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleSet("name", () -> {
            validator.ruleForString(Person::getName).isNotEmpty().startsWith("s").length(5, 10);
        });

        List<ValidationFailure> validationFailures = validator.validate(person, "name");

        assertEquals(1, validationFailures.size());
    }

    @Test
    void customValidatorRuleSet() {
        Person person = new Person("sean", 32, null);

        PersonValidator validator = new PersonValidator();
        List<ValidationFailure> validationFailures = validator.validate(person, "address");

        assertEquals(1, validationFailures.size());
    }

    @Test
    void validateAndThrow() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotEmpty().startsWith("s").length(5, 10).withMessage("Name must be between 5 and 10 yo");
        validator.ruleForInteger(Person::getAge).isPositive();

        ValidationException exception = assertThrows(ValidationException.class, () -> validator.validateAndThrow(person));

        assertEquals(1, exception.getFailures().size());
    }

    // TODO: should not throw when using rule set and not set

    @Test
    void validateAndThrowWithStringRuleSet() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleSet("name", () -> {
            validator.ruleForString(Person::getName).isNotEmpty().startsWith("s").length(5, 10);
        });

        ValidationException exception = assertThrows(ValidationException.class, () ->validator.validateAndThrow(person, "name"));

        assertEquals(1, exception.getFailures().size());
    }

    @Test
    void validateAndThrowWithListRuleSet() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleSet("name", () -> {
            validator.ruleForString(Person::getName).isNotEmpty().startsWith("s").length(5, 10);
        });

        ValidationException exception = assertThrows(ValidationException.class, () ->validator.validateAndThrow(person, Collections.singletonList("name")));

        assertEquals(1, exception.getFailures().size());
    }

    private class PersonValidator extends DefaultValidator<Person> {

        PersonValidator() {
            ruleForString(Person::getName).isNotEmpty().startsWith("s").length(0, 4);
            ruleForObject(Person::getAddress).isNull();
            ruleForInteger(Person::getAge).isPositive();
            ruleForBoolean(Person::isMarried).isFalse();
            // TODO: fix this ... ruleForZonedDateTime(Person::getDob).isInThePast();
            ruleForZonedDateTime(Person::getSignedIn).isAfter(ZonedDateTime.now().minusDays(1));
            ruleForMap(Person::getPets).isNotEmpty().forEachKey(isLowerCase()).forEachValue(length(0, 5));
            // ruleForIterable(p -> p.getChildren()).isNotNull().forEach(startsWith("S"));
            ruleForByteArray(Person::getBytes).isNotNull();

            include(new PersonAgeValidator());

//            TODO: how to mix and match ruleset with when?
//            TODO: how to include validator for a nested field such as address?

            ruleSet("address", () ->  {
                ruleForObject(Person::getAddress).isNotNull();
            });
        }
    }

    private static class PersonAgeValidator extends DefaultValidator<Person> {

        PersonAgeValidator() {
            // TODO: is kind of sucks...is there a better way to do this?
            //ruleForInteger(Person::getAge).must(this::beOver18);

            // This isnt an improvement
            // ruleForInteger(Person::getAge).must(a -> beOver18(a));

            // this is pretty good.
            ruleForInteger(Person::getAge).must(isOver18());
        }

        protected boolean beOver18(int year) {
            return year > 18;
        }

        // TODO: if I pull this out I can make this static
        private Predicate<Integer> isOver18() {
            return age -> age > 18;
        }
    }

    // TODO: should this also be part of the `include` API or should be be something else?
    // Fluentvalidator has both include (used for the same type) and setValidator (used for different types)
    private static class AddressValidator extends DefaultValidator<Address> {
        public AddressValidator() {
            when(Objects::nonNull, () -> {
                ruleForString(Address::getStreet1).isNotNull();
                ruleForString(Address::getCity).isNotNull();
                ruleForString(Address::getState).isNotNull();
                ruleForString(Address::getZip).isNotNull();
            });
        }
    }

    // TODO: add test for iterable with different types ie subclasses

}
