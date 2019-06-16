package validators.rulefor.localdate;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterLocalDateTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Person p = new Person(null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDate(Person::getBirthday).isAfter(LocalDate.now());

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Person p = new Person(LocalDate.now());

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDate(Person::getBirthday).isAfter(LocalDate.now().plusDays(1));

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        LocalDate date = LocalDate.now();
        Person p = new Person(date);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDate(Person::getBirthday).isAfter(date);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Person p = new Person(LocalDate.now());

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForLocalDate(Person::getBirthday).isAfter(LocalDate.now().minusDays(1));

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForLocalDate(Person::getBirthday).isAfter(null));
    }
}
