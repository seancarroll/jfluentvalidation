package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class LongSubject<T> extends AbstractComparableNumber<LongSubject<T>, T, Long> {

//    private static final Long ZERO = (long) 0.0;
//    private static final Long ONE = 1L;

    public LongSubject(PropertyRule<T, Long> rule) {
        super(LongSubject.class, rule);
    }

//    @Override
//    public LongSubject<T> isZero() {
//        // TODO: what should the localization key be for this?
//        // Do we want to tie it to constraint name?
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public LongSubject<T> isNotZero() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public LongSubject<T> isOne() {
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public LongSubject<T> isNotOne() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public LongSubject<T> isPositive() {
//        rule.addConstraint(new IsGreaterThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public LongSubject<T> isNotPositive() {
//        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public LongSubject<T> isNegative() {
//        rule.addConstraint(new IsLessThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public LongSubject<T> isNotNegative() {
//        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }

    @Override
    protected Long zero() {
        return 0L;
    }

    @Override
    protected Long one() {
        return 1L;
    }
}
