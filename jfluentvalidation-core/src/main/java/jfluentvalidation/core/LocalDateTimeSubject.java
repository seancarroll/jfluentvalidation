package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsTodayLocalDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// TODO: isEquals vs equals

/**
 * @param <T> the type of the instance
 */
public class LocalDateTimeSubject<T>
    extends AbstractComparableSubject<LocalDateTimeSubject<T>, T, LocalDateTime> {

    public LocalDateTimeSubject(PropertyRule<T, LocalDateTime> rule) {
        super(LocalDateTimeSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isBefore(LocalDateTime other) {
        rule.addConstraint(new IsBeforeLocalDateTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isBeforeOrEqualTo(LocalDateTime other) {
        rule.addConstraint(new IsBeforeOrEqualLocalDateTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isAfter(LocalDateTime other) {
        rule.addConstraint(new IsAfterLocalDateTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isAfterOrEqualTo(LocalDateTime other) {
        rule.addConstraint(new IsAfterOrEqualLocalDateTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isInThePast() {
        rule.addConstraint(new IsBeforeLocalDateTimeConstraint<>(() -> LocalDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isInThePastOrPresent() {
        rule.addConstraint(new IsBeforeOrEqualLocalDateTimeConstraint<>(() -> LocalDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isInThePastOrToday() {
        rule.addConstraint(new IsBeforeOrEqualLocalDateTimeConstraint<>(() ->
            LocalDateTime.now(rule.getRuleOptions().getClockReference()).truncatedTo(ChronoUnit.DAYS), ChronoUnit.DAYS)
        );
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isInTheFuture() {
        rule.addConstraint(new IsAfterLocalDateTimeConstraint<>(() -> LocalDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isInTheFutureOrPresent() {
        rule.addConstraint(new IsAfterOrEqualLocalDateTimeConstraint<>(() -> LocalDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isInTheFutureOrToday() {
        rule.addConstraint(new IsAfterOrEqualLocalDateTimeConstraint<>(() ->
            LocalDateTime.now(rule.getRuleOptions().getClockReference()).truncatedTo(ChronoUnit.DAYS), ChronoUnit.DAYS)
        );
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalDateTimeSubject<T> isToday() {
        rule.addConstraint(new IsTodayLocalDateTimeConstraint<>(rule.getRuleOptions().getClockReference()));
        return myself;
    }
}
