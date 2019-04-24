package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: what should the type hierarchy be?

/**
 *
 */
public class IntegerSubject<T> extends AbstractComparableNumber<IntegerSubject<T>, T, Integer> {

//    private static final Integer ZERO = 0;
//    private static final Integer ONE = 1;

    public IntegerSubject(PropertyRule<T, Integer> rule) {
        super(IntegerSubject.class, rule);
    }

//    @Override
//    public IntegerSubject<T> isZero() {
//        // TODO: what should the localization key be for this?
//        // Do we want to tie it to constraint name?
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject<T> isNotZero() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject<T> isOne() {
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject<T> isNotOne() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject<T> isPositive() {
//        rule.addConstraint(new IsGreaterThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject<T> isNotPositive() {
//        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject<T> isNegative() {
//        rule.addConstraint(new IsLessThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public IntegerSubject<T> isNotNegative() {
//        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }

    @Override
    protected Integer zero() {
        return 0;
    }

    @Override
    protected Integer one() {
        return 1;
    }
}
