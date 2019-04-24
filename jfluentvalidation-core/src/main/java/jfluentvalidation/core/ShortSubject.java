package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: verify we can handle primitives

/**
 *
 */
public class ShortSubject<T> extends AbstractComparableNumber<ShortSubject<T>, T, Short> {

//    private static final Short ZERO = 0;
//    private static final Short ONE = 1;

    public ShortSubject(PropertyRule<T, Short> rule) {
        super(ShortSubject.class, rule);
    }

//    @Override
//    public ShortSubject<T> isZero() {
//        // TODO: what should the localization key be for this?
//        // Do we want to tie it to constraint name?
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ShortSubject<T> isNotZero() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ShortSubject<T> isOne() {
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public ShortSubject<T> isNotOne() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public ShortSubject<T> isPositive() {
//        rule.addConstraint(new IsGreaterThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ShortSubject<T> isNotPositive() {
//        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ShortSubject<T> isNegative() {
//        rule.addConstraint(new IsLessThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ShortSubject<T> isNotNegative() {
//        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }


    @Override
    protected Short zero() {
        return 0;
    }

    @Override
    protected Short one() {
        return 1;
    }
}
