package jfluentvalidation.validators.rulefor.localdatetime;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterLocalDateTimeTest {

    private static final LocalDateTime ACTUAL = LocalDateTime.of(2019, 06, 15, 0, 0, 0);
    private static final LocalDateTime BEFORE = LocalDateTime.of(2019, 06, 14, 0, 0, 0);
    private static final LocalDateTime AFTER = LocalDateTime.of(2019, 06, 16, 0, 0, 0);

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Person p = new Person(null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDateTime(Person::getBirthday).isAfter(LocalDateTime.now());

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDateTime(Person::getBirthday).isAfter(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDateTime(Person::getBirthday).isAfter(ACTUAL);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDateTime(Person::getBirthday).isAfter(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDateTime(Person::getBirthday).isAfter(null));
    }

}
