package jfluentvalidation.validators.rulefor.calendar;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterCalendarTest {

    private static final Calendar ACTUAL;
    private static final Calendar BEFORE;
    private static final Calendar AFTER;

    static {
        ACTUAL = Calendar.getInstance();

        BEFORE = Calendar.getInstance();
        BEFORE.add(Calendar.DATE, -1);

        AFTER = Calendar.getInstance();
        AFTER.add(Calendar.DATE, 1);
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Person p = new Person(null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfter(Calendar.getInstance());

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfter(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Person p = new Person(ACTUAL);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfter(ACTUAL);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Person p = new Person(Calendar.getInstance());

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfter(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForCalendar(Person::getBirthday).isAfter(null));
    }

}
