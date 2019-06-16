package validators.rulefor.calendar;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterOrEqualTest {

    private static final Calendar REFERENCE;
    private static final Calendar BEFORE;
    private static final Calendar AFTER;

    static {
        REFERENCE = Calendar.getInstance();

        BEFORE = Calendar.getInstance();
        BEFORE.add(Calendar.DATE, -1);

        AFTER = Calendar.getInstance();
        AFTER.add(Calendar.DATE, 1);
    }

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Person p = new Person(null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfterOrEqual(Calendar.getInstance());

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Person p = new Person(REFERENCE);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfterOrEqual(AFTER);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldNotReturnFailureWhenActualEqualsGivenDate() {
        Person p = new Person(REFERENCE);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfterOrEqual(REFERENCE);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Person p = new Person(REFERENCE);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfterOrEqual(BEFORE);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForCalendar(Person::getBirthday).isAfterOrEqual(null));
    }
}
