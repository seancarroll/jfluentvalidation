package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.LocalTime;

/**
 * Constraints for {@code LocalTime} subjects.
 *
 * @param <T> the type of the instance
 */
public class LocalTimeSubject<T>
    extends AbstractTemporalSubject<LocalTimeSubject<T>, T, LocalTime> {

    public LocalTimeSubject(PropertyRule<T, LocalTime> rule) {
        super(LocalTimeSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isBefore(LocalTime other) {
        rule.addConstraint(new IsBeforeLocalTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isBeforeOrEqualTo(LocalTime other) {
        rule.addConstraint(new IsBeforeOrEqualLocalTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isAfter(LocalTime other) {
        rule.addConstraint(new IsAfterLocalTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isAfterOrEqualTo(LocalTime other) {
        rule.addConstraint(new IsAfterOrEqualLocalTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isInThePast() {
        rule.addConstraint(new IsBeforeLocalTimeConstraint<>(() -> LocalTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isInThePastOrPresent() {
        rule.addConstraint(new IsBeforeOrEqualLocalTimeConstraint<>(() -> LocalTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isInTheFuture() {
        rule.addConstraint(new IsAfterLocalTimeConstraint<>(() -> LocalTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public LocalTimeSubject<T> isInTheFutureOrPresent() {
        rule.addConstraint(new IsAfterOrEqualLocalTimeConstraint<>(() -> LocalTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

}
