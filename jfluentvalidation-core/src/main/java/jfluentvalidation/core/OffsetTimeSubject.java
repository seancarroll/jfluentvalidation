package jfluentvalidation.core;

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

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public OffsetTimeSubject<T> isBefore(OffsetTime other) {
        rule.addConstraint(new IsBeforeOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject<T> isBeforeOrEqual(OffsetTime other) {
        rule.addConstraint(new IsBeforeOrEqualOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject<T> isAfter(OffsetTime other) {
        rule.addConstraint(new IsAfterOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject<T> isAfterOrEqual(OffsetTime other) {
        rule.addConstraint(new IsAfterOrEqualOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(OffsetTime.now());
    }

    public OffsetTimeSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(OffsetTime.now());
    }

    public OffsetTimeSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(OffsetTime.now());
    }

    public OffsetTimeSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(OffsetTime.now());
    }

}
