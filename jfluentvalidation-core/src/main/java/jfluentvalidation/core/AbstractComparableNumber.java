package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.*;

import java.util.function.Function;

public abstract class AbstractComparableNumber<S extends AbstractComparableNumber<S, A>, A extends Number & Comparable<? super A>>
    extends Subject<S, A>
    implements NumberSubject<S, A>, ComparableSubject<S, A> {

    public AbstractComparableNumber(Class<?> selfType, Function<Object, A> propertyFunc, String propertyName) {
        super(selfType, propertyFunc, propertyName);
    }

    @Override
    public S isEqualAccordingToCompareTo(A other) {
        constraints.add(new IsEqualAccordingToCompareToConstraint(other));
        return myself;
    }

    @Override
    public S isNotEqualAccordingToCompareTo(A other) {
        constraints.add(new IsNotEqualAccordingToCompareToConstraint(other));
        return myself;
    }

    @Override
    public S isLessThan(A other) {
        constraints.add(new IsLessThanConstraint(other));
        return myself;
    }

    @Override
    public S isLessThanOrEqualTo(A other) {
        constraints.add(new IsLessThanOrEqualToConstraint(other));
        return myself;
    }

    @Override
    public S isGreaterThan(A other) {
        constraints.add(new IsGreaterThanConstraint(other));
        return myself;
    }

    @Override
    public S isGreaterThanOrEqualTo(A other) {
        constraints.add(new IsGreaterThanOrEqualToConstraint(other));
        return myself;
    }

    @Override
    public S isBetween(A startInclusive, A endInclusive) {
        constraints.add(new IsBetweenConstraint(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public S isStrictlyBetween(A startExclusive, A endExclusive) {
        constraints.add(new IsBetweenConstraint(startExclusive, endExclusive, true, true));
        return myself;
    }

    @Override
    public S isBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        constraints.add(new IsBetweenConstraint(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    public S isNotBetwen(A start, A end) {
        // TODO: should it be inclusive start and end?
        constraints.add(new IsNotBetweenConstraint(start, end, true, true));
        return myself;
    }

    public S isNotBetwen(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        // TODO: should it be inclusive start and end?
        constraints.add(new IsNotBetweenConstraint(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public S isZero() {
        // TODO: what should the localization key be for this?
        // Do we want to tie it to constraint name?
        constraints.add(new IsEqualAccordingToCompareToConstraint(0));
        return myself;
    }

    @Override
    public S isNotZero() {
        constraints.add(new IsNotEqualAccordingToCompareToConstraint(0));
        return myself;
    }

    @Override
    public S isOne() {
        constraints.add(new IsEqualAccordingToCompareToConstraint(1));
        return myself;
    }

    @Override
    public S isNotOne() {
        constraints.add(new IsNotEqualAccordingToCompareToConstraint(0));
        return myself;
    }

    @Override
    public S isPositive() {
        constraints.add(new IsGreaterThanConstraint(0));
        return myself;
    }

    @Override
    public S isNotPositive() {
        constraints.add(new IsLessThanOrEqualToConstraint(0));
        return myself;
    }

    @Override
    public S isNegative() {
        constraints.add(new IsLessThanConstraint(0));
        return myself;
    }

    @Override
    public S isNotNegative() {
        constraints.add(new IsGreaterThanOrEqualToConstraint(0));
        return myself;
    }

    @Override
    public S isCloseTo() {
        return null;
    }

    @Override
    public S isNotCloseTo() {
        return null;
    }
}
