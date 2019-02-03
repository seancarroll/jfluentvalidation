package jfluentvalidation.core;

import java.util.function.Function;

public class BooleanSubject extends Subject<BooleanSubject, Boolean> implements ComparableSubject<BooleanSubject, Boolean> {

    public BooleanSubject(Function propertyFunc, String propertyName) {
        super(BooleanSubject.class, propertyFunc, propertyName);
    }

    // TODO: isEqual and isNotEqual
    public BooleanSubject isTrue() {
        // TODO: should we just use a isEquals(true) constraint or have a IsTrue() constraint?
        //constraints.add(IsEqualsConstraint(true));
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
    @Override
    public BooleanSubject isEqualAccordingToCompareTo(Boolean other) {
        return null;
    }

    @Override
    public BooleanSubject isNotEqualAccordingToCompareTo(Boolean other) {
        return null;
    }

    @Override
    public BooleanSubject isLessThan(Boolean other) {
        return null;
    }

    @Override
    public BooleanSubject isLessThanOrEqualTo(Boolean other) {
        return null;
    }

    @Override
    public BooleanSubject isGreaterThan(Boolean other) {
        return null;
    }

    @Override
    public BooleanSubject isGreaterThanOrEqualTo(Boolean other) {
        return null;
    }

    @Override
    public BooleanSubject isBetween(Boolean startInclusive, Boolean endInclusive) {
        return null;
    }

    @Override
    public BooleanSubject isStrictlyBetween(Boolean startExclusive, Boolean endExclusive) {
        return null;
    }

    @Override
    public BooleanSubject isBetween(Boolean start, Boolean end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public BooleanSubject isNotBetween(Boolean startInclusive, Boolean endInclusive) {
        return null;
    }

    @Override
    public BooleanSubject isNotBetween(Boolean start, Boolean end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
