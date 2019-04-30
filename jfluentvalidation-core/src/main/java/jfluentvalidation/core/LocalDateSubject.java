package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterLocalDateConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalDateConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalDateConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalDateConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.LocalDate;

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

/**
 *
 * @param <T>  the type of the instance
 */
public class LocalDateSubject<T>
    extends AbstractComparableSubject<LocalDateSubject<T>, T, LocalDate> {

    public LocalDateSubject(PropertyRule<T, LocalDate> rule) {
        super(LocalDateSubject.class, rule);
    }

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public LocalDateSubject<T> isBefore(LocalDate localDate) {
        rule.addConstraint(new IsBeforeLocalDateConstraint<>(localDate));
        return  myself;
    }

    public LocalDateSubject<T> isBeforeOrEqual(LocalDate localDate) {
        rule.addConstraint(new IsBeforeOrEqualLocalDateConstraint<>(localDate));
        return  myself;
    }

    public LocalDateSubject<T> isAfter(LocalDate localDate) {
        rule.addConstraint(new IsAfterLocalDateConstraint<>(localDate));
        return  myself;
    }

    public LocalDateSubject<T> isAfterOrEqual(LocalDate localDate) {
        rule.addConstraint(new IsAfterOrEqualLocalDateConstraint<>(localDate));
        return  myself;
    }

    public LocalDateSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalDate.now());
    }

    public LocalDateSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(LocalDate.now());
    }

    public LocalDateSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalDate.now());
    }

    public LocalDateSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(LocalDate.now());
    }

}
