package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class BooleanSubject<T> extends Subject<BooleanSubject<T>, T, Boolean> implements ComparableSubject<BooleanSubject<T>, T, Boolean> {

    public BooleanSubject(PropertyRule<T, Boolean> rule) {
        super(BooleanSubject.class, rule);
    }

    // TODO: isEqual and isNotEqual

    public BooleanSubject<T> isTrue() {
        // TODO: should we just use a isEquals(true) constraint or have a IsTrue() constraint?
        isEquals(true);
        return myself;
    }

    public BooleanSubject<T> isFalse() {
        // TODO: should we just use a isEquals(false) constraint or have a IsFalse() constraint?
        isEquals(false);
        return myself;
    }

    // TODO: do these makes sense for boolean? While boolean does implement Comparable perhaps here is another way to
    // split compareTo from these others? Maybe they belong to ComparableNumber?
    // the isBetween and isNotBetween dont make sense to me for boolean
    // Maybe ComparableSubject just has compareTo and all the other methods dont need to belong to an interface?
    @Override
    public BooleanSubject<T> isEqualAccordingToCompareTo(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isNotEqualAccordingToCompareTo(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isLessThan(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isLessThanOrEqualTo(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isGreaterThan(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isGreaterThanOrEqualTo(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isBetween(Boolean startInclusive, Boolean endInclusive) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isStrictlyBetween(Boolean startExclusive, Boolean endExclusive) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isBetween(Boolean start, Boolean end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isNotBetween(Boolean startInclusive, Boolean endInclusive) {
        return myself;
    }

    @Override
    public BooleanSubject<T> isNotBetween(Boolean start, Boolean end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
