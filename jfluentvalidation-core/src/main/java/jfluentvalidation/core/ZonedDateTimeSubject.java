package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterOrEqualZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsInTheFutureZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsTodayZonedDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

// TODO: still some unchecked assignments

/**
 * Constraints for {@link ZonedDateTime} subjects.
 *
 * @param <T> the type of the instance
 */
public class ZonedDateTimeSubject<T>
    extends AbstractTemporalSubject<ZonedDateTimeSubject<T>, T, ZonedDateTime> {

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
        rule.addConstraint(new IsBeforeOrEqualZonedDateTimeConstraint<>(() ->
            ZonedDateTime.now(rule.getRuleOptions().getClockReference()).truncatedTo(ChronoUnit.DAYS), ChronoUnit.DAYS)
        );
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInTheFuture() {
        rule.addConstraint(new IsInTheFutureZonedDateTimeConstraint<>(() -> ZonedDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInTheFutureOrPresent() {
        rule.addConstraint(new IsAfterOrEqualZonedDateTimeConstraint<>(() -> ZonedDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isInTheFutureOrToday() {
        rule.addConstraint(new IsAfterOrEqualZonedDateTimeConstraint<>(() ->
            ZonedDateTime.now(rule.getRuleOptions().getClockReference()).truncatedTo(ChronoUnit.DAYS), ChronoUnit.DAYS)
        );
        return myself;
    }

    @CanIgnoreReturnValue
    public ZonedDateTimeSubject<T> isToday() {
        rule.addConstraint(new IsTodayZonedDateTimeConstraint<>(rule.getRuleOptions().getClockReference()));
        return myself;
    }

}
