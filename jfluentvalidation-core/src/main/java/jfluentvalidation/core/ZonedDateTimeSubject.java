package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterOrEqualZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeZonedDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.ZonedDateTime;

// TODO: isEquals vs equals
// TODO: still some unchecked assignments

/**
 *
 * @param <T>  the type of the instance
 */
public class ZonedDateTimeSubject<T>
    extends AbstractComparableSubject<ZonedDateTimeSubject<T>, T, ZonedDateTime> {

    public ZonedDateTimeSubject(PropertyRule<T, ZonedDateTime> rule) {
        super(ZonedDateTimeSubject.class, rule);
    }

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isBefore(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsBeforeZonedDateTimeConstraint<>(zonedDateTime));
        return  myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isBeforeOrEqualTo(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsBeforeOrEqualZonedDateTimeConstraint<>(zonedDateTime));
        return  myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isAfter(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsAfterZonedDateTimeConstraint<>(zonedDateTime));
        return  myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isAfterOrEqualTo(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsAfterOrEqualZonedDateTimeConstraint<>(zonedDateTime));
        return  myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(ZonedDateTime.now());
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqualTo(ZonedDateTime.now());
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(ZonedDateTime.now());
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqualTo(ZonedDateTime.now());
    }

}
