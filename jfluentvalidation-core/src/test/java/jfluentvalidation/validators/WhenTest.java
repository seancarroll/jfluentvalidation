package jfluentvalidation.validators;

import jfluentvalidation.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WhenTest {

    @Test
    void shouldApplyConstraintWhenPredicateIsTrue() {
        Person person = new Person(null, 32);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotNull().when(p -> p.getAge() != 0);

        ValidationResult validationResult = validator.validate(person);

        assertTrue(validationResult.hasFailures());
    }

    @Test
    void shouldNotApplyConstraintWhenPredicateIsFalse() {
        Person person = new Person(null, 32);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).isNotNull().when(p -> p.getAge() == 0);

        ValidationResult validationResult = validator.validate(person);

        assertFalse(validationResult.hasFailures());
    }

    @Test
    void predicateShouldApplyToAllConstraintsByDefault() {
        Person person = new Person("sean", 32);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).startsWith("a").hasLengthGreaterThan(5).when(p -> p.getAge() > 30);

        ValidationResult validationResult = validator.validate(person);

        assertEquals(2, validationResult.getViolations().size());
    }

    @Test
    void constraintShouldOnlyApplyToValidatorWhenApplyToAllIsFalse() {
        Person person = new Person("sean", 32);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForString(Person::getName).startsWith("a").hasLengthGreaterThan(5).when(p -> p.getAge() > 35, false);

        ValidationResult validationResult = validator.validate(person);

        assertEquals(1, validationResult.getViolations().size());
    }

    @Test
    void shouldBeAbleToConfigureWhenClauseWithinCustomValidator() {
        PersonValidator validator = new PersonValidator();

        Person sean = new Person("sean", 32);
        ValidationResult validationResultForSean = validator.validate(sean);
        assertFalse(validationResultForSean.hasFailures());

        Person bobby = new Person("bobby", 36);
        ValidationResult validationResultForBobby = validator.validate(bobby);
        assertEquals(2, validationResultForBobby.getViolations().size());
    }


    private static class PersonValidator extends DefaultValidator<Person> {
        PersonValidator() {
            when(p -> p.getAge() > 35, () -> {
                ruleForString(Person::getName).isNotEmpty().startsWith("s");
                ruleForObject(Person::getAddress).isNotNull();
            });
        }
    }
}
