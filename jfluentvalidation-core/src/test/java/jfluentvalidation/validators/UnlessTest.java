package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnlessTest {

    @Test
    void shouldNotApplyConstraintWhenPredicateIsTrue() {
        Person person = new Person(null, 0, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotNull().unless(p -> p.getAge() == 0);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertTrue(validationFailures.isEmpty());
    }

    @Test
    void shouldApplyConstraintWhenPredicateIsFalse() {
        Person person = new Person(null, 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotNull().unless(p -> p.getAge() == 0);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertFalse(validationFailures.isEmpty());
    }

    @Test
    void predicateShouldApplyToAllConstraintsByDefault() {
        Person person = new Person("sean", 0, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotNull().startsWith("a").hasLengthGreaterThan(5).unless(p -> p.getAge() == 0);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertTrue(validationFailures.isEmpty());
    }

    @Test
    void constraintShouldOnlyApplyToValidatorWhenApplyToAllIsFalse() {
        Person person = new Person("sean", 32, null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).startsWith("a").hasLengthGreaterThan(5).unless(p -> p.getAge() > 30, false);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }

    @Test
    void shouldNotIncludeUnlessValidationsWhenPredicateIsFalse() {
        Person person = new Person("bobby", 20, null);

        PersonValidator validator = new PersonValidator();
        List<ValidationFailure> validationFailures = validator.validate(person);

        assertTrue(validationFailures.isEmpty());
    }

    @Test
    void customValidatorWithUnlessBlock() {
        Person person = new Person("bobby", 32, null);

        PersonValidator validator = new PersonValidator();
        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(2, validationFailures.size());
    }



    private class PersonValidator extends DefaultValidator<Person> {
        PersonValidator() {
            unless(p -> p.getAge() < 25, () -> {
                ruleForString(Person::getName).isNotEmpty().startsWith("s");
                ruleForObject(Person::getAddress).isNotNull();
            });
        }
    }
}
