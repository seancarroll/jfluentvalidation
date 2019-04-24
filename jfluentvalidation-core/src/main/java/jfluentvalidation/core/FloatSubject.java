package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: verify we can handle primitives
/**
 *
 */
public class FloatSubject<T> extends AbstractComparableNumber<FloatSubject<T>, T, Float> {

//    private static final Float ZERO = (float) 0.0;
//    private static final Float ONE = 1.0f;

    public FloatSubject(PropertyRule<T, Float> rule) {
        super(FloatSubject.class, rule);
    }

//    @Override
//    public FloatSubject<T> isZero() {
//        // TODO: what should the localization key be for this?
//        // Do we want to tie it to constraint name?
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public FloatSubject<T> isNotZero() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public FloatSubject<T> isOne() {
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public FloatSubject<T> isNotOne() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public FloatSubject<T> isPositive() {
//        rule.addConstraint(new IsGreaterThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public FloatSubject<T> isNotPositive() {
//        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public FloatSubject<T> isNegative() {
//        rule.addConstraint(new IsLessThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public FloatSubject<T> isNotNegative() {
//        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }

    @Override
    protected Float zero() {
        return 0.0f;
    }

    @Override
    protected Float one() {
        return 1.0f;
    }
}
