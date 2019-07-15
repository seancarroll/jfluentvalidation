package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.time.IsAfterOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.OffsetDateTime;

// TODO: isEquals vs equals

/**
 *
 * @param <T>  the type of the instance
 */
public class OffsetDateTimeSubject<T>
    extends AbstractComparableSubject<OffsetDateTimeSubject<T>, T, OffsetDateTime> {

    public OffsetDateTimeSubject(PropertyRule<T, OffsetDateTime> rule) {
        super(OffsetDateTimeSubject.class, rule);
    }

    @CanIgnoreReturnValue
    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public OffsetDateTimeSubject<T> isBefore(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsBeforeOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isBeforeOrEqualTo(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsBeforeOrEqualOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isAfter(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsAfterOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isAfterOrEqualTo(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsAfterOrEqualOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(OffsetDateTime.now());
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqualTo(OffsetDateTime.now());
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(OffsetDateTime.now());
    }

    @CanIgnoreReturnValue
    public OffsetDateTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqualTo(OffsetDateTime.now());
    }

}
