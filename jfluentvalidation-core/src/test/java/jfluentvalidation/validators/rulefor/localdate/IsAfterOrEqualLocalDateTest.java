package jfluentvalidation.validators.rulefor.localdate;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterOrEqualLocalDateTest {

    private static final LocalDate ACTUAL = LocalDate.of(2019, 6, 15);
    private static final LocalDate BEFORE = LocalDate.of(2019, 6, 14);
    private static final LocalDate AFTER = LocalDate.of(2019, 6, 16);

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Person p = new Person(null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDate(Person::getBirthday).isAfterOrEqual(LocalDate.now());

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDate(Person::getBirthday).isAfterOrEqual(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDate(Person::getBirthday).isAfterOrEqual(ACTUAL);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDate(Person::getBirthday).isAfterOrEqual(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDate(Person::getBirthday).isAfterOrEqual(null));
    }

}
