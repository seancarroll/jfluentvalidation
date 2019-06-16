package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTests {

    @Test
    void defaultValidator() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(5, 10).withMessage("Name must be between 5 and 10 yo");
        validator.ruleForInteger(p -> p.getAge()).isPositive();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }

    @Test
    void staticFactory() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = DefaultValidator.forClass(Person.class);
        validator.ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(5, 10);
        validator.ruleForInteger(p -> p.getAge()).isPositive().isBetween(1, 50);

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
            validator.ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(5, 10);
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

    // TODO: update test
//    @Test
//    void whenTest() {
//        Address address = new Address("", "", "", "");
//
//        AddressValidator validator = new AddressValidator();
//        List<ValidationFailure> validationFailures = validator.validate(address);
//        System.out.println(validationFailures);
//        assertEquals(1, validationFailures.length());
//    }

    private class PersonValidator extends DefaultValidator<Person> {

        public PersonValidator() {
            ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(0, 4);
            ruleForObject(p -> p.getAddress()).isNull();
            ruleForInteger(p -> p.getAge()).isPositive();
            ruleForBoolean(p -> p.isMarried()).isFalse();
            // TODO: fix this ... ruleForZonedDateTime(Person::getDob).isInThePast();
            ruleForZonedDateTime(p -> p.getSignedIn()).isAfter(ZonedDateTime.now().minusDays(1));
            ruleForMap(p -> p.getPets()).isNotEmpty().forEachKey(isLowerCase()).forEachValue(length(0, 5));
            ruleForIterable(p -> p.getChildren()).isNotNull().forEach(startsWith("S"));
            ruleForByteArray(p -> p.getBytes()).isNotNull();

            include(new PersonAgeValidator());

//            TODO: how to mix and match ruleset with when?
//            TODO: how to include validator for a nested field such as address?

            ruleSet("address", () ->  {
                ruleForObject(p -> p.getAddress()).isNotNull();
            });
        }
    }

    private class PersonAgeValidator extends DefaultValidator<Person> {

        public PersonAgeValidator() {
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
    private class AddressValidator extends DefaultValidator<Address> {
        public AddressValidator() {
            when(address -> address != null, () -> {
                ruleForString(a -> a.getStreet1()).isNotNull();
                ruleForString(a -> a.getCity()).isNotNull();
                ruleForString(a -> a.getState()).isNotNull();
                ruleForString(a -> a.getZip()).isNotNull();
            });
        }
    }

    // TODO: add test for iterable with different types ie subclasses

}
