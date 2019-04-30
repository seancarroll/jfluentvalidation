package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.LocalTime;

// TODO: isEquals vs equals

/**
 *
 * @param <T>  the type of the instance
 */
public class LocalTimeSubject<T>
    extends AbstractComparableSubject<LocalTimeSubject<T>, T, LocalTime> {

    public LocalTimeSubject(PropertyRule<T, LocalTime> rule) {
        super(LocalTimeSubject.class, rule);
    }

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    // before and after use compareTo...do we want to keep both?
    public LocalTimeSubject<T> isBefore(LocalTime other) {
        rule.addConstraint(new IsBeforeLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject<T> isBeforeOrEqual(LocalTime other) {
        rule.addConstraint(new IsBeforeOrEqualLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject<T> isAfter(LocalTime other) {
        rule.addConstraint(new IsAfterLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject<T> isAfterOrEqual(LocalTime other) {
        rule.addConstraint(new IsAfterOrEqualLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalTime.now());
    }

    public LocalTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(LocalTime.now());
    }

    public LocalTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalTime.now());
    }

    public LocalTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(LocalTime.now());
    }

}
