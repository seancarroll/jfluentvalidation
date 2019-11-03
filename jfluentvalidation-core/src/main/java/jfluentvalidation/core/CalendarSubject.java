package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.common.Dates;
import jfluentvalidation.constraints.time.IsAfterCalendarConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualCalendarConstraint;
import jfluentvalidation.constraints.time.IsBeforeCalendarConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualCalendarConstraint;
import jfluentvalidation.constraints.time.IsCloseToCalendarConstraint;
import jfluentvalidation.constraints.time.IsTodayCalendarConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import static jfluentvalidation.common.Dates.calendarFromClock;

/**
 * Constraints for {@link Calendar} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class CalendarSubject<T>
    extends AbstractComparableSubject<CalendarSubject<T>, T, Calendar> {

    public CalendarSubject(PropertyRule<T, Calendar> rule) {
        super(CalendarSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isBefore(Calendar other) {
        rule.addConstraint(new IsBeforeCalendarConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isBeforeOrEqualTo(Calendar other) {
        rule.addConstraint(new IsBeforeOrEqualCalendarConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isAfter(Calendar other) {
        rule.addConstraint(new IsAfterCalendarConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isAfterOrEqualTo(Calendar other) {
        rule.addConstraint(new IsAfterOrEqualCalendarConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInTheFuture() {
        rule.addConstraint(new IsAfterCalendarConstraint<>(() -> calendarFromClock(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInTheFutureOrPresent() {
        rule.addConstraint(new IsAfterOrEqualCalendarConstraint<>(() -> calendarFromClock(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInTheFutureOrToday() {
        rule.addConstraint(new IsAfterOrEqualCalendarConstraint<>(() ->
            Dates.truncateTime(calendarFromClock(rule.getRuleOptions().getClockReference())), ChronoUnit.DAYS)
        );
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInThePast() {
        rule.addConstraint(new IsBeforeCalendarConstraint<>(() -> calendarFromClock(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInThePastOrPresent() {
        rule.addConstraint(new IsBeforeOrEqualCalendarConstraint<>(() -> calendarFromClock(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInThePastOrToday() {
        rule.addConstraint(new IsBeforeOrEqualCalendarConstraint<>(() ->
            Dates.truncateTime(calendarFromClock(rule.getRuleOptions().getClockReference())), ChronoUnit.DAYS)
        );
        return myself;
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isToday() {
        rule.addConstraint(new IsTodayCalendarConstraint<>(rule.getRuleOptions().getClockReference()));
        return myself;
    }

    // TODO: use long for millis? use Temporal / ChronoUnit?
    @CanIgnoreReturnValue
    public CalendarSubject<T> isCloseTo(Calendar other, long offset, boolean strict) {
        rule.addConstraint(new IsCloseToCalendarConstraint<>(other, offset, strict));
        return myself;
    }

}
