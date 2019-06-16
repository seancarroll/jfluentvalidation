package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsBeforeLocalDateTimeTest {

    private static final LocalDateTime ACTUAL = LocalDateTime.of(2019, 6, 15, 0, 0, 0);
    private static final LocalDateTime BEFORE = LocalDateTime.of(2019, 6, 14, 0, 0, 0);
    private static final LocalDateTime AFTER = LocalDateTime.of(2019, 6, 16, 0, 0, 0);

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Person p = new Person(null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDateTime(Person::getBirthday).isBefore(LocalDateTime.now());

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyBeforeGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDateTime(Person::getBirthday).isBefore(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDateTime(Person::getBirthday).isBefore(ACTUAL);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsBeforeGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDateTime(Person::getBirthday).isBefore(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDateTime(Person::getBirthday).isBefore(null));
    }
}
