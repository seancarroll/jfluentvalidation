package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.comparable.ComparableConstraints;
import jfluentvalidation.rules.PropertyRule;

/**
 * Constraints for {@code boolean} subjects.
 *
 * @param <T>  the type of the instance
 */
public class BooleanSubject<T> extends Subject<BooleanSubject<T>, T, Boolean> implements ComparableSubject<BooleanSubject<T>, T, Boolean> {

    public BooleanSubject(PropertyRule<T, Boolean> rule) {
        super(BooleanSubject.class, rule);
    }

    // TODO: isEqual and isNotEqual

    @CanIgnoreReturnValue
    public BooleanSubject<T> isTrue() {
        return isEquals(true);
    }

    @CanIgnoreReturnValue
    public BooleanSubject<T> isFalse() {
        return isEquals(false);
    }

    // TODO: do these makes sense for boolean? While boolean does implement Comparable perhaps here is another way to
    // split compareTo from these others? Maybe they belong to ComparableNumber?
    // the isBetween and isNotBetween dont make sense to me for boolean
    // Maybe ComparableSubject just has compareTo and all the other methods dont need to belong to an interface?
    @Override
    public BooleanSubject<T> isEqualAccordingToCompareTo(Boolean other) {
        rule.addConstraint(ComparableConstraints.isEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public BooleanSubject<T> isNotEqualAccordingToCompareTo(Boolean other) {
        rule.addConstraint(ComparableConstraints.isNotEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public BooleanSubject<T> isLessThan(Boolean other) {
        rule.addConstraint(ComparableConstraints.isLessThan(other));
        return myself;
    }

    @Override
    public BooleanSubject<T> isLessThanOrEqualTo(Boolean other) {
        rule.addConstraint(ComparableConstraints.isLessThanOrEqualTo(other));
        return myself;
    }

    @Override
    public BooleanSubject<T> isGreaterThan(Boolean other) {
        rule.addConstraint(ComparableConstraints.isGreaterThan(other));
        return myself;
    }

    @Override
    public BooleanSubject<T> isGreaterThanOrEqualTo(Boolean other) {
        rule.addConstraint(ComparableConstraints.isGreaterThanOrEqualTo(other));
        return myself;
    }

    @Override
    public BooleanSubject<T> isBetween(Boolean startInclusive, Boolean endInclusive) {
        rule.addConstraint(ComparableConstraints.isBetween(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public BooleanSubject<T> isStrictlyBetween(Boolean startExclusive, Boolean endExclusive) {
        rule.addConstraint(ComparableConstraints.isStrictlyBetween(startExclusive, endExclusive));
        return myself;
    }

    @Override
    public BooleanSubject<T> isBetween(Boolean start, Boolean end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public BooleanSubject<T> isNotBetween(Boolean startInclusive, Boolean endInclusive) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isNotBetween(Boolean start, Boolean end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
