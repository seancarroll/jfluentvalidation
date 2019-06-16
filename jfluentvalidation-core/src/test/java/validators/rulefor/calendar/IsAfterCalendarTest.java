package validators.rulefor.calendar;

import jfluentvalidation.ValidationFailure;
import jfluentvalidation.validators.DefaultValidator;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsAfterCalendarTest {

    @Test
    void shouldReturnFailureWhenActualIsNull() {
        Calendar t = Calendar.getInstance();
        Person p = new Person(null);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfter(Calendar.getInstance());

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldReturnFailureWhenActualIsNotStrictlyAfterGivenDate() {
        Person p = new Person(Calendar.getInstance());

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);

        Calendar givenDate = Calendar.getInstance();
        givenDate.add(Calendar.DATE, 1);
        validator.ruleForCalendar(Person::getBirthday).isAfter(givenDate);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }


    @Test
    void shouldReturnFailureWhenActualEqualsGivenDate() {
        Calendar date = Calendar.getInstance();
        Person p = new Person(date);

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        validator.ruleForCalendar(Person::getBirthday).isAfter(date);

        List<ValidationFailure> failures = validator.validate(p);

        assertFalse(failures.isEmpty());
    }

    @Test
    void shouldNotReturnFailureWhenActualDateIsAfterGivenDate() {
        Person p = new Person(Calendar.getInstance());

        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);

        Calendar givenDate = Calendar.getInstance();
        givenDate.add(Calendar.DATE, -1);
        validator.ruleForCalendar(Person::getBirthday).isAfter(givenDate);

        List<ValidationFailure> failures = validator.validate(p);

        assertTrue(failures.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenGivenDateIsNull() {
        DefaultValidator<Person> validator = new DefaultValidator<>(Person.class);
        assertThrows(NullPointerException.class, () -> validator.ruleForCalendar(Person::getBirthday).isAfter(null));
    }

//    @Test
//    public void should_fail_if_actual_is_not_strictly_after_given_date() {
//        AssertionInfo info = someInfo();
//        Date other = parseDate("2022-01-01");
//        try {
//            dates.assertIsAfter(info, actual, other);
//        } catch (AssertionError e) {
//            verify(failures).failure(info, shouldBeAfter(actual, other));
//            return;
//        }
//        failBecauseExpectedAssertionErrorWasNotThrown();
//    }
//
//    @Test
//    public void should_fail_if_actual_is_equals_to_given_date() {
//        AssertionInfo info = someInfo();
//        Date other = parseDate("2011-01-15");
//        try {
//            dates.assertIsAfter(info, actual, other);
//        } catch (AssertionError e) {
//            verify(failures).failure(info, shouldBeAfter(actual, other));
//            return;
//        }
//        failBecauseExpectedAssertionErrorWasNotThrown();
//    }
//
//    @Test
//    public void should_throw_error_if_given_date_is_null() {
//        assertThatNullPointerException().isThrownBy(() -> dates.assertIsAfter(someInfo(), actual, null))
//            .withMessage(dateToCompareActualWithIsNull());
//    }
//
//    @Test
//    public void should_fail_if_actual_is_null() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> dates.assertIsAfter(someInfo(), null, parseDate("2010-01-01")))
//            .withMessage(actualIsNull());
//    }
//
//    @Test
//    public void should_pass_if_actual_is_strictly_after_given_date() {
//        dates.assertIsAfter(someInfo(), actual, parseDate("2000-01-01"));
//    }
//
//    @Test
//    public void should_fail_if_actual_is_not_strictly_after_given_date_according_to_custom_comparison_strategy() {
//        AssertionInfo info = someInfo();
//        Date other = parseDate("2022-01-01");
//        try {
//            datesWithCustomComparisonStrategy.assertIsAfter(info, actual, other);
//        } catch (AssertionError e) {
//            verify(failures).failure(info, shouldBeAfter(actual, other, yearAndMonthComparisonStrategy));
//            return;
//        }
//        failBecauseExpectedAssertionErrorWasNotThrown();
//    }
//
//    @Test
//    public void should_fail_if_actual_is_equals_to_given_date_according_to_custom_comparison_strategy() {
//        AssertionInfo info = someInfo();
//        Date other = parseDate("2011-01-31");
//        try {
//            datesWithCustomComparisonStrategy.assertIsAfter(info, actual, other);
//        } catch (AssertionError e) {
//            verify(failures).failure(info, shouldBeAfter(actual, other, yearAndMonthComparisonStrategy));
//            return;
//        }
//        failBecauseExpectedAssertionErrorWasNotThrown();
//    }
//
//    @Test
//    public void should_throw_error_if_given_date_is_null_whatever_custom_comparison_strategy_is() {
//        assertThatNullPointerException().isThrownBy(() -> datesWithCustomComparisonStrategy.assertIsAfter(someInfo(),
//            actual,
//            null))
//            .withMessage(dateToCompareActualWithIsNull());
//    }
//
//    @Test
//    public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
//        assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> datesWithCustomComparisonStrategy.assertIsAfter(someInfo(), null, parseDate("2010-01-01")))
//            .withMessage(actualIsNull());
//    }
//
//    @Test
//    public void should_pass_if_actual_is_strictly_after_given_date_according_to_custom_comparison_strategy() {
//        datesWithCustomComparisonStrategy.assertIsAfter(someInfo(), actual, parseDate("2000-01-01"));
//    }


}
