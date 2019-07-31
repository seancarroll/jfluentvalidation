package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
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
    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isBefore(LocalTime other) {
        rule.addConstraint(new IsBeforeLocalTimeConstraint<>(other));
        return  myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isBeforeOrEqualTo(LocalTime other) {
        rule.addConstraint(new IsBeforeOrEqualLocalTimeConstraint<>(other));
        return  myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isAfter(LocalTime other) {
        rule.addConstraint(new IsAfterLocalTimeConstraint<>(other));
        return  myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isAfterOrEqualTo(LocalTime other) {
        rule.addConstraint(new IsAfterOrEqualLocalTimeConstraint<>(other));
        return  myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalTime.now());
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqualTo(LocalTime.now());
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalTime.now());
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqualTo(LocalTime.now());
    }

}
