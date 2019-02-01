package jfluentvalidation.core;

import java.util.function.Function;

// TODO: what should the type hierarchy be?
public class IntegerSubject extends AbstractComparableNumber { //Subject<IntegerSubject, Integer> implements NumberSubject<IntegerSubject, Integer>, ComparableSubject<IntegerSubject, Integer> {

    public IntegerSubject(Function func, String propertyName) {
        super(IntegerSubject.class, func, propertyName);
    }

//    @Override
//    public IntegerSubject isZero() {
//        // TODO: what should the localization key be for this?
//        // Do we want to tie it to constraint name?
//        constraints.add(new IsEqualAccordingToCompareToConstraint(0));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isNotZero() {
//        constraints.add(new IsNotEqualAccordingToCompareToConstraint(0));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isOne() {
//        constraints.add(new IsEqualAccordingToCompareToConstraint(1));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isNotOne() {
//        constraints.add(new IsNotEqualAccordingToCompareToConstraint(0));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isPositive() {
//        constraints.add(new IsGreaterThanConstraint(0));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isNotPositive() {
//        constraints.add(new IsLessThanOrEqualToConstraint(0));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isNegative() {
//        constraints.add(new IsLessThanConstraint(0));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isNotNegative() {
//        constraints.add(new IsGreaterThanOrEqualToConstraint(0));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isCloseTo() {
//        return null;
//    }
//
//    @Override
//    public IntegerSubject isNotCloseTo() {
//        return null;
//    }
//
//    @Override
//    public IntegerSubject isEqualAccordingToCompareTo(Integer other) {
//        return null;
//    }
//
//    @Override
//    public IntegerSubject isNotEqualAccordingToCompareTo(Integer other) {
//        return null;
//    }
//
//    @Override
//    public IntegerSubject isLessThan(Integer other) {
//        constraints.add(new IsLessThanConstraint(other));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isLessThanOrEqualTo(Integer other) {
//        constraints.add(new IsLessThanOrEqualToConstraint(other));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isGreaterThan(Integer other) {
//        constraints.add(new IsGreaterThanConstraint(other));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isGreaterThanOrEqualTo(Integer other) {
//        constraints.add(new IsGreaterThanOrEqualToConstraint(other));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isBetween(Integer startInclusive, Integer endInclusive) {
//        constraints.add(new IsBetweenConstraint(startInclusive, endInclusive, true, true));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isStrictlyBetween(Integer startExclusive, Integer endExclusive) {
//        constraints.add(new IsBetweenConstraint(startExclusive, endExclusive, true, true));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject isBetween(Integer start, Integer end, boolean inclusiveStart, boolean inclusiveEnd) {
//        constraints.add(new IsBetweenConstraint(start, end, inclusiveStart, inclusiveEnd));
//        return myself;
//    }
}
