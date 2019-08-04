package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.comparable.IsGreaterThanConstraint;
import jfluentvalidation.constraints.comparable.IsGreaterThanOrEqualToConstraint;
import jfluentvalidation.constraints.comparable.IsLessThanConstraint;
import jfluentvalidation.constraints.comparable.IsLessThanOrEqualToConstraint;
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
    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public DateSubject<T> isBefore(Date other) {
        rule.addConstraint(new IsLessThanConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isBeforeOrEqualTo(Date other) {
        // TODO: should this match or be closer to java8 Temporal classes
        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isAfter(Date other) {
        rule.addConstraint(new IsGreaterThanConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isAfterOrEqualTo(Date other) {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInTheFuture() {
        // TODO: should we have a Clock?
        return isAfter(new Date());
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqualTo(new Date());
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInTheFutureOrToday() {
        // TODO: clock from context/provider
        throw new RuntimeException("not implemented");
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInThePast() {
        // TODO: should we have a Clock?
        return isBefore(new Date());
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqualTo(new Date());
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInThePastOrToday() {
        // TODO: clock from context/provider
        throw new RuntimeException("not implemented");
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isToday() {
        // TODO:
        return myself;
    }
}
