package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.LocalDateTime;

// TODO: isEquals vs equals

/**
 *
 * @param <T>  the type of the instance
 */
public class LocalDateTimeSubject<T>
    extends AbstractComparableSubject<LocalDateTimeSubject<T>, T, LocalDateTime> {

    public LocalDateTimeSubject(PropertyRule<T, LocalDateTime> rule) {
        super(LocalDateTimeSubject.class, rule);
    }

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public LocalDateTimeSubject isBefore(LocalDateTime other) {
        rule.addConstraint(new IsBeforeLocalDateTimeConstraint<>(other));
        return  myself;
    }

    public LocalDateTimeSubject<T> isBeforeOrEqualTo(LocalDateTime other) {
        rule.addConstraint(new IsBeforeOrEqualLocalDateTimeConstraint<>(other));
        return  myself;
    }

    public LocalDateTimeSubject<T> isAfter(LocalDateTime other) {
        rule.addConstraint(new IsAfterLocalDateTimeConstraint<>(other));
        return  myself;
    }

    public LocalDateTimeSubject<T> isAfterOrEqualTo(LocalDateTime other) {
        rule.addConstraint(new IsAfterOrEqualLocalDateTimeConstraint<>(other));
        return  myself;
    }

    public LocalDateTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalDateTime.now());
    }

    public LocalDateTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqualTo(LocalDateTime.now());
    }

    public LocalDateTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalDateTime.now());
    }

    public LocalDateTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqualTo(LocalDateTime.now());
    }

}
