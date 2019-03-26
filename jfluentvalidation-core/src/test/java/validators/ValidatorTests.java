package validators;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.AbstractValidator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorTests {

//    TODO: would love to find a suitable way to support this usage pattern
//    Could we just force callers to pass the type into the constructor? Seems silly but do other libraries do this?
//    @Test
//    void noCustomClass() {
//        Person person = new Person("sean", 32, "");
//
//        DefaultValidator<Person> validator = new DefaultValidator<>();
//        validator.ruleForString(p -> p.getName()).isNotEmpty().startsWith("s").length(5, 10);
//        validator.ruleForInteger(p -> p.getAge()).isPositive();
//        validator.ruleForString(Person::getAddress).isEmpty();
//
//        List<ValidationFailure> validationFailures = validator.validate(person);
//
//        assertEquals(1, validationFailures.size());
//    }


    @Test
    void customValidator() {
        Person m = new Person("sean", 32, null);
        m.setChilren(Arrays.asList("Shawn", "Sally"));

        PersonValidator validator = new PersonValidator();

        List<ValidationFailure> validationFailures = validator.validate(m);

        assertEquals(1, validationFailures.size());
    }

    private class PersonValidator extends AbstractValidator<Person> {

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
            ruleForIterable(p -> p.getChilren()).isNotNull().isNotEmpty();

            //include(new PersonAgeValidator());
        }
    }

    private class PersonAgeValidator extends AbstractValidator<Person> {

        public PersonAgeValidator() {
            super();
            // TODO: is kind of sucks...is there a better way to do this?
            //ruleForInteger(Person::getAge).must(this::beOver18);

            // This isnt an improvement
            // ruleForInteger(Person::getAge).must(a -> beOver18(a));

            // this is pretty good.
            // ruleForInteger(Person::getAge).must(isOver18());
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
    private class AddressValidator extends AbstractValidator<Address> {
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
