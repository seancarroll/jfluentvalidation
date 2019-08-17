package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.*;
import jfluentvalidation.rules.PropertyRule;

import java.time.ZonedDateTime;

// TODO: isEquals vs equals
// TODO: still some unchecked assignments

/**
 * Constraints for {@link ZonedDateTime} subjects.
 *
 * @param <T> the type of the instance
 */
public class ZonedDateTimeSubject<T>
    extends AbstractComparableSubject<ZonedDateTimeSubject<T>, T, ZonedDateTime> {

    public ZonedDateTimeSubject(PropertyRule<T, ZonedDateTime> rule) {
        super(ZonedDateTimeSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isBefore(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsBeforeZonedDateTimeConstraint<>(zonedDateTime));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isBeforeOrEqualTo(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsBeforeOrEqualZonedDateTimeConstraint<>(zonedDateTime));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isAfter(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsAfterZonedDateTimeConstraint<>(zonedDateTime));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isAfterOrEqualTo(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsAfterOrEqualZonedDateTimeConstraint<>(zonedDateTime));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInThePast() {
        rule.addConstraint(new IsBeforeZonedDateTimeConstraint<>(() -> ZonedDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInThePastOrPresent() {
        rule.addConstraint(new IsBeforeOrEqualZonedDateTimeConstraint<>(() -> ZonedDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInThePastOrToday() {
        // TODO: clock from context/provider
        throw new RuntimeException("not implemented");
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInTheFuture() {
        rule.addConstraint(new IsAfterZonedDateTimeConstraint<>(() -> ZonedDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInTheFutureOrPresent() {
        rule.addConstraint(new IsAfterOrEqualZonedDateTimeConstraint<>(() -> ZonedDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInTheFutureOrToday() {
        // TODO: clock from context/provider
        throw new RuntimeException("not implemented");
    }


    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isToday() {
        rule.addConstraint(new IsTodayZonedDateTimeConstraint<>(rule.getRuleOptions().getClockReference()));
        return myself;
    }

}
