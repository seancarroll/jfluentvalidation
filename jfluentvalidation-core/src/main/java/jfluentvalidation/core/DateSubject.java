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
 *
 * @param <T>  the type of the instance
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
    public DateSubject<T> isInThePast() {
        // TODO: should we have a Clock?
        return isBefore(new Date());
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isToday() {
        // TODO:
        return myself;
    }

    @CanIgnoreReturnValue
    public DateSubject<T> isInTheFuture() {
        // TODO: should we have a Clock?
        return isAfter(new Date());
    }


}
