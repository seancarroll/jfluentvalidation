package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterDateConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualDateConstraint;
import jfluentvalidation.constraints.time.IsBeforeDateConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualDateConstraint;
import jfluentvalidation.constraints.time.IsTodayDateConstraint;
import jfluentvalidation.rules.PropertyRule;

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
        // TODO: clock from context/provider
        throw new RuntimeException("not implemented");
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
        // TODO: clock from context/provider
        throw new RuntimeException("not implemented");
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isToday() {
        rule.addConstraint(new IsTodayDateConstraint<>(rule.getRuleOptions().getClockReference()));
        return myself;
    }
}
