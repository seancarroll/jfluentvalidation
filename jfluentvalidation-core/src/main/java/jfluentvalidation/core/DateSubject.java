package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.common.Dates;
import jfluentvalidation.constraints.time.IsAfterDateConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualDateConstraint;
import jfluentvalidation.constraints.time.IsBeforeDateConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualDateConstraint;
import jfluentvalidation.constraints.time.IsCloseToDateConstraint;
import jfluentvalidation.constraints.time.IsTodayDateConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.temporal.ChronoUnit;
import java.util.Date;

// rather than isLessThan, isGreaterThan etc should we have isBefore, isAfter, etc? How to separate from other Comparable?
// NumberComparable vs TemporalComparable/some other name

/**
 * Constraints for {@link Date} typed subjects.
 *
 * @param <T>  the type of the instance
 * @see java.util.Date
 */
public class DateSubject<T> extends AbstractComparableSubject<DateSubject<T>, T, Date> {

    public DateSubject(PropertyRule<T, Date> rule) {
        super(DateSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isBefore(Date other) {
        rule.addConstraint(new IsBeforeDateConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isBeforeOrEqualTo(Date other) {
        rule.addConstraint(new IsBeforeOrEqualDateConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isAfter(Date other) {
        rule.addConstraint(new IsAfterDateConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isAfterOrEqualTo(Date other) {
        rule.addConstraint(new IsAfterOrEqualDateConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInTheFuture() {
        rule.addConstraint(new IsAfterDateConstraint<>(() -> Date.from(rule.getRuleOptions().getClockReference().instant())));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInTheFutureOrPresent() {
        rule.addConstraint(new IsAfterOrEqualDateConstraint<>(() -> Date.from(rule.getRuleOptions().getClockReference().instant())));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInTheFutureOrToday() {
        rule.addConstraint(new IsAfterOrEqualDateConstraint<>(() ->
            Dates.truncateTime(Date.from(rule.getRuleOptions().getClockReference().instant())), ChronoUnit.DAYS)
        );
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInThePast() {
        rule.addConstraint(new IsBeforeDateConstraint<>(() -> Date.from(rule.getRuleOptions().getClockReference().instant())));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInThePastOrPresent() {
        rule.addConstraint(new IsBeforeOrEqualDateConstraint<>(() -> Date.from(rule.getRuleOptions().getClockReference().instant())));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInThePastOrToday() {
        rule.addConstraint(new IsBeforeOrEqualDateConstraint<>(() ->
            Dates.truncateTime(Date.from(rule.getRuleOptions().getClockReference().instant())), ChronoUnit.DAYS)
        );
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isToday() {
        rule.addConstraint(new IsTodayDateConstraint<>(rule.getRuleOptions().getClockReference()));
        return myself;
    }

    // TOOD: use long for millis? use Temporal / ChronoUnit?
    @CanIgnoreReturnValue
    public DateSubject<T> isCloseTo(Date other, long offset, boolean strict) {
        rule.addConstraint(new IsCloseToDateConstraint<>(other, offset, strict));
        return myself;
    }
}
