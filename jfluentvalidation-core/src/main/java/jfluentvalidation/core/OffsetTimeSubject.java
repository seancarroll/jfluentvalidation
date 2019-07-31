package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.OffsetTime;

// TODO: isEquals vs equals

/**
 *
 * @param <T>  the type of the instance
 */
public class OffsetTimeSubject<T>
    extends AbstractComparableSubject<OffsetTimeSubject<T>, T, OffsetTime> {

    public OffsetTimeSubject(PropertyRule<T, OffsetTime> rule) {
        super(OffsetTimeSubject.class, rule);
    }

    @CanIgnoreReturnValue
    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public OffsetTimeSubject<T> isBefore(OffsetTime other) {
        rule.addConstraint(new IsBeforeOffsetTimeConstraint<>(other));
        return  myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isBeforeOrEqualTo(OffsetTime other) {
        rule.addConstraint(new IsBeforeOrEqualOffsetTimeConstraint<>(other));
        return  myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isAfter(OffsetTime other) {
        rule.addConstraint(new IsAfterOffsetTimeConstraint<>(other));
        return  myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isAfterOrEqualTo(OffsetTime other) {
        rule.addConstraint(new IsAfterOrEqualOffsetTimeConstraint<>(other));
        return  myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(OffsetTime.now());
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqualTo(OffsetTime.now());
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(OffsetTime.now());
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqualTo(OffsetTime.now());
    }

}
