package validators;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static jfluentvalidation.constraints.charsequence.CharSequenceConstraints.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTests {

    @Test
    void defaultValidator() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(5, 10);
        validator.ruleForInteger(p -> p.getAge()).isPositive();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }

    @Test
    void staticFactory() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = DefaultValidator.forClass(Person.class);
        validator.ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(5, 10);
        validator.ruleForInteger(p -> p.getAge()).isPositive();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }

    @Test
    void customValidator() {
        Person person = new Person("sean", 32, null);
        person.setChilren(Arrays.asList("Shawn", "Sally"));

        Map<String, String> pets = new HashMap<>();
        pets.put("otis", "dog");
        pets.put("milo", "cat");
        person.setPets(pets);

        PersonValidator validator = new PersonValidator();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }

    @Test
    void ageValidator() {
        Person person = new Person("sean", 32, null);

        PersonAgeValidator validator = new PersonAgeValidator();

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(0, validationFailures.size());
    }


    private class PersonValidator extends DefaultValidator<Person> {

        public PersonValidator() {
            // TODO: ugh! I dont like this. How to avoid requiring a call to super?
            super();
            ruleForString(p -> p.getName()).isEmpty().startsWith("s").length(0, 4);
            // ruleForString(p -> p.getAddress()).isNotNull();
            ruleForObject(p -> p.getAddress()).isNull();
            ruleForInteger(p -> p.getAge()).isPositive();
            ruleForBoolean(p -> p.isMarried()).isFalse();
            ruleForZonedDateTime(p -> p.getSignedIn()).isAfter(ZonedDateTime.now().minusDays(1));
            ruleForMap(p -> p.getPets()).isEmpty();
            ruleForIterable(p -> p.getChilren()).isNotNull().forEach(startsWith("S"));

            include(new PersonAgeValidator());

            ruleSet("rules", () -> {
                ruleForObject(p -> p.getAddress()).isNull();
            });
        }
    }

    private class PersonAgeValidator extends DefaultValidator<Person> {

        public PersonAgeValidator() {
            super();
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
            when(address -> address != null, address -> {
                ruleForString(a -> a.getStreet1()).isNotNull();
                ruleForString(a -> a.getCity()).isNotNull();
                ruleForString(a -> a.getState()).isNotNull();
                ruleForString(a -> a.getZip()).isNotNull();
            });
        }
    }

}
