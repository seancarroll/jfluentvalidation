package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class BooleanSubject extends Subject<BooleanSubject, Boolean> implements ComparableSubject<BooleanSubject, Boolean> {

    public BooleanSubject(PropertyRule<?, Boolean> rule) {
        super(BooleanSubject.class, rule);
    }

    // TODO: isEqual and isNotEqual

    public BooleanSubject isTrue() {
        // TODO: should we just use a isEquals(true) constraint or have a IsTrue() constraint?
        isEquals(true);
        return myself;
    }

    public BooleanSubject isFalse() {
        // TODO: should we just use a isEquals(false) constraint or have a IsFalse() constraint?
        isEquals(false);
        return myself;
    }

    // TODO: do these makes sense for boolean? While boolean does implement Comparable perhaps here is another way to
    // split compareTo from these others? Maybe they belong to ComparableNumber?
    // the isBetween and isNotBetween dont make sense to me for boolean
    // Maybe ComparableSubject just has compareTo and all the other methods dont need to belong to an interface?
    @Override
    public BooleanSubject isEqualAccordingToCompareTo(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject isNotEqualAccordingToCompareTo(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject isLessThan(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject isLessThanOrEqualTo(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject isGreaterThan(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject isGreaterThanOrEqualTo(Boolean other) {
        return myself;
    }

    @Override
    public BooleanSubject isBetween(Boolean startInclusive, Boolean endInclusive) {
        return myself;
    }

    @Override
    public BooleanSubject isStrictlyBetween(Boolean startExclusive, Boolean endExclusive) {
        return myself;
    }

    @Override
    public BooleanSubject isBetween(Boolean start, Boolean end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public BooleanSubject isNotBetween(Boolean startInclusive, Boolean endInclusive) {
        return myself;
    }

    @Override
    public BooleanSubject isNotBetween(Boolean start, Boolean end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
