package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterCalendarConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualCalendarConstraint;
import jfluentvalidation.constraints.time.IsBeforeCalendarConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualCalendarConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Calendar;

/**
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
        // TODO: clock from context/provider
        return isAfter(Calendar.getInstance());
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqualTo(Calendar.getInstance());
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(Calendar.getInstance());
    }

    @CanIgnoreReturnValue
    public CalendarSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqualTo(Calendar.getInstance());
    }

}
