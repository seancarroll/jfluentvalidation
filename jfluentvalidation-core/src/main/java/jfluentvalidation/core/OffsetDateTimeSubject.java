package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsTodayOffsetDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.OffsetDateTime;

// TODO: isEquals vs equals

/**
 * Constraints for {@link OffsetDateTime} subjects.
 *
 * @param <T> the type of the instance
 */
public class OffsetDateTimeSubject<T>
    extends AbstractComparableSubject<OffsetDateTimeSubject<T>, T, OffsetDateTime> {

    public OffsetDateTimeSubject(PropertyRule<T, OffsetDateTime> rule) {
        super(OffsetDateTimeSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isBefore(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsBeforeOffsetDateTimeConstraint<>(offsetDateTime));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isBeforeOrEqualTo(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsBeforeOrEqualOffsetDateTimeConstraint<>(offsetDateTime));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isAfter(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsAfterOffsetDateTimeConstraint<>(offsetDateTime));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isAfterOrEqualTo(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsAfterOrEqualOffsetDateTimeConstraint<>(offsetDateTime));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInThePast() {
        rule.addConstraint(new IsBeforeOffsetDateTimeConstraint<>(() -> OffsetDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInThePastOrPresent() {
        rule.addConstraint(new IsBeforeOrEqualOffsetDateTimeConstraint<>(() -> OffsetDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInThePastOrToday() {
        // TODO: clock from context/provider
        throw new RuntimeException("not implemented");
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInTheFuture() {
        rule.addConstraint(new IsAfterOffsetDateTimeConstraint<>(() -> OffsetDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInTheFutureOrPresent() {
        rule.addConstraint(new IsAfterOrEqualOffsetDateTimeConstraint<>(() -> OffsetDateTime.now(rule.getRuleOptions().getClockReference())));
        return myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInTheFutureOrToday() {
        // TODO: clock from context/provider
        throw new RuntimeException("not implemented");
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isToday() {
        rule.addConstraint(new IsTodayOffsetDateTimeConstraint<>(rule.getRuleOptions().getClockReference()));
        return myself;
    }

}
