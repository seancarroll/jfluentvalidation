package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: verify we can handle primitives
/**
 *
 */
public class DoubleSubject<T> extends AbstractComparableNumber<DoubleSubject<T>, T, Double> {

//    private static final Double ZERO = 0.0;
//    private static final Double ONE = 1.0;

    public DoubleSubject(PropertyRule<T, Double> rule) {
        super(DoubleSubject.class, rule);
    }

//    @Override
//    public DoubleSubject<T> isZero() {
//        // TODO: what should the localization key be for this?
//        // Do we want to tie it to constraint name?
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public DoubleSubject<T> isNotZero() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public DoubleSubject<T> isOne() {
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public DoubleSubject<T> isNotOne() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public DoubleSubject<T> isPositive() {
//        rule.addConstraint(new IsGreaterThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public DoubleSubject<T> isNotPositive() {
//        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public DoubleSubject<T> isNegative() {
//        rule.addConstraint(new IsLessThanConstraint<>(ZERO));
//        return myself;
//    }



    @Override
    protected Double zero() {
        return 0.0d;
    }

    @Override
    protected Double one() {
        return 1.0d;
    }
}
