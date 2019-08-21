package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhenTest {

    @Test
    void shouldApplyConstraintWhenPredicateIsTrue() {
        Person person = new Person(null, 32);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotNull().when(p -> p.getAge() != 0);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertFalse(validationFailures.isEmpty());
    }

    @Test
    void shouldNotApplyConstraintWhenPredicateIsFalse() {
        Person person = new Person(null, 32);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotNull().when(p -> p.getAge() == 0);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertTrue(validationFailures.isEmpty());
    }

    @Test
    void predicateShouldApplyToAllConstraintsByDefault() {
        Person person = new Person("sean", 32);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).startsWith("a").hasLengthGreaterThan(5).when(p -> p.getAge() > 30);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(2, validationFailures.size());
    }

    @Test
    void constraintShouldOnlyApplyToValidatorWhenApplyToAllIsFalse() {
        Person person = new Person("sean", 32);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).startsWith("a").hasLengthGreaterThan(5).when(p -> p.getAge() > 35, false);

        List<ValidationFailure> validationFailures = validator.validate(person);

        assertEquals(1, validationFailures.size());
    }

    @Test
    void shouldBeAbleToConfigureWhenClauseWithinCustomValidator() {
        PersonValidator validator = new PersonValidator();

        Person sean = new Person("sean", 32);
        List<ValidationFailure> validationFailuresForSean = validator.validate(sean);
        assertTrue(validationFailuresForSean.isEmpty());

        Person bobby = new Person("bobby", 36);
        List<ValidationFailure> validationFailuresForBobby = validator.validate(bobby);
        assertEquals(2, validationFailuresForBobby.size());
    }


    private class PersonValidator extends DefaultValidator<Person> {
        PersonValidator() {
            when(p -> p.getAge() > 35, () -> {
                ruleForString(Person::getName).isNotEmpty().startsWith("s");
                ruleForObject(Person::getAddress).isNotNull();
            });
        }
    }
}
