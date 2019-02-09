package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterLocalDateConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalDateConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalDateConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalDateConstraint;

import java.time.LocalDate;
import java.util.function.Function;

// TODO: isEquals vs equals
// From javadoc: This class does not store or represent a time or time-zone.
// Instead, it is a description of the date, as used for birthdays.
// It cannot represent an instant on the time-line without additional information such as an offset or time-zone.
// As such isInTheFuture/isInThePast dont make sense for this?
// according to javax.validation.constraints LocalDate is supported...hmmm
// java 8 bean validation added them because of the support for TemporalAccessors with a ClockProvider -- JSR 310
// Research bean validation extending @Past/@Future to support nowIsValid
// Hibernate apparently had an issue and needs to work around this but seems like this was fixed for hibernate validator 6.0
// FutureOrPresent / PastOrPresent
// Hibernate validator appears to use compareTo for comparisons instead of .isBefore / .isAfter which based on source code
// is not the same
// Hibernate validator does have a concept of getEffectiveTemporalValidationToTolerance (which is a duration)
// which it ues when constructing the reference clock via Clock.offset
public class LocalDateSubject
    extends Subject<LocalDateSubject, LocalDate>
    implements ComparableSubject<LocalDateSubject, LocalDate> {

    public LocalDateSubject(Function propertyFunc, String propertyName) {
        super(LocalDate.class, propertyFunc, propertyName);
    }

    public LocalDateSubject isBefore(LocalDate localDate) {
        constraints.add(new IsBeforeLocalDateConstraint(localDate));
        return  myself;
    }

    public LocalDateSubject isBeforeOrEqual(LocalDate localDate) {
        constraints.add(new IsBeforeOrEqualLocalDateConstraint(localDate));
        return  myself;
    }

    public LocalDateSubject isAfter(LocalDate localDate) {
        constraints.add(new IsAfterLocalDateConstraint(localDate));
        return  myself;
    }

    public LocalDateSubject isAfterOrEqual(LocalDate localDate) {
        constraints.add(new IsAfterOrEqualLocalDateConstraint(localDate));
        return  myself;
    }

    public LocalDateSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalDate.now());
    }

    public LocalDateSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(LocalDate.now());
    }

    public LocalDateSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalDate.now());
    }

    public LocalDateSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(LocalDate.now());
    }

    @Override
    public LocalDateSubject isEqualAccordingToCompareTo(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isNotEqualAccordingToCompareTo(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isLessThan(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isLessThanOrEqualTo(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isGreaterThan(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isGreaterThanOrEqualTo(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isBetween(LocalDate startInclusive, LocalDate endInclusive) {
        return null;
    }

    @Override
    public LocalDateSubject isStrictlyBetween(LocalDate startExclusive, LocalDate endExclusive) {
        return null;
    }

    @Override
    public LocalDateSubject isBetween(LocalDate start, LocalDate end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public LocalDateSubject isNotBetween(LocalDate startInclusive, LocalDate endInclusive) {
        return null;
    }

    @Override
    public LocalDateSubject isNotBetween(LocalDate start, LocalDate end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}