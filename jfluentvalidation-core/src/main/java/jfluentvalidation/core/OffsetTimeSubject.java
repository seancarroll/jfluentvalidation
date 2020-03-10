package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsInTheFutureOffsetTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.OffsetTime;

/**
 * Constraints for {@link OffsetTime} subjects.
 *
 * @param <T>  the type of the instance
 */
public class OffsetTimeSubject<T>
    extends AbstractTemporalSubject<OffsetTimeSubject<T>, T, OffsetTime> {

    public OffsetTimeSubject(PropertyRule<T, OffsetTime> rule) {
        super(OffsetTimeSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isBefore(OffsetTime other) {
        rule.addConstraint(new IsBeforeOffsetTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isBeforeOrEqualTo(OffsetTime other) {
        rule.addConstraint(new IsBeforeOrEqualOffsetTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isAfter(OffsetTime other) {
        rule.addConstraint(new IsAfterOffsetTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isAfterOrEqualTo(OffsetTime other) {
        rule.addConstraint(new IsAfterOrEqualOffsetTimeConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isInThePast() {
        rule.addConstraint(new IsBeforeOffsetTimeConstraint<>(() -> OffsetTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isInThePastOrPresent() {
        rule.addConstraint(new IsBeforeOrEqualOffsetTimeConstraint<>(() -> OffsetTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isInTheFuture() {
        rule.addConstraint(new IsInTheFutureOffsetTimeConstraint<>(() -> OffsetTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetTimeSubject<T> isInTheFutureOrPresent() {
        rule.addConstraint(new IsAfterOrEqualOffsetTimeConstraint<>(() -> OffsetTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

}
