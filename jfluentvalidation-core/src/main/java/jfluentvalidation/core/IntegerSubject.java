package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.IsGreaterThanConstraint;

import java.util.function.Function;

// TODO: what should the type hierarchy be?
public class IntegerSubject extends Subject<IntegerSubject, Integer> implements NumberSubject<IntegerSubject, Integer>, ComparableSubject<IntegerSubject, Integer>  {

    public IntegerSubject(Function func, String propertyName) {
        super(IntegerSubject.class, func, propertyName);
    }

//    public IntegerSubject(AtomicInteger actual) {
//        this(actual == null ? null : actual.get());
//    }


    @Override
    public IntegerSubject isZero() {
        return null;
    }

    @Override
    public IntegerSubject isNotZero() {
        return null;
    }

    @Override
    public IntegerSubject isOne() {
        return null;
    }

    @Override
    public IntegerSubject isNotOne() {
        return null;
    }

    @Override
    public IntegerSubject isPositive() {
        constraints.add(new IsGreaterThanConstraint(0));
        return myself;
    }

    @Override
    public IntegerSubject isNotPositive() {
        return null;
    }

    @Override
    public IntegerSubject isNegative() {
        return null;
    }

    @Override
    public IntegerSubject isNotNegative() {
        return null;
    }

    @Override
    public IntegerSubject isBetween() {
        return null;
    }

    @Override
    public IntegerSubject isStrictlyBetween() {
        return null;
    }

    @Override
    public IntegerSubject isCloseTo() {
        return null;
    }

    @Override
    public IntegerSubject isNotCloseTo() {
        return null;
    }


    @Override
    public IntegerSubject isEqualAccordingToCompareTo(Integer other) {
        return null;
    }

    @Override
    public IntegerSubject isNotEqualAccordingToCompareTo(Integer other) {
        return null;
    }

    @Override
    public IntegerSubject isLessThan(Integer other) {
        return null;
    }

    @Override
    public IntegerSubject isLessThanOrEqualTo(Integer other) {
        return null;
    }

    @Override
    public IntegerSubject isGreaterThan(Integer other) {
        return null;
    }

    @Override
    public IntegerSubject isGreaterThanOrEqualTo(Integer other) {
        return null;
    }

    @Override
    public IntegerSubject isBetween(Integer startInclusive, Integer endInclusive) {
        return null;
    }

    @Override
    public IntegerSubject isStrictlyBetween(Integer startExclusive, Integer endExclusive) {
        return null;
    }

    @Override
    public IntegerSubject isBetween(Integer start, Integer end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
