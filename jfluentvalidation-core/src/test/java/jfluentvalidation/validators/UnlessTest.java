package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(validationFailures.get(0).getErrorMessage().contains("StartsWith"));
    }

}