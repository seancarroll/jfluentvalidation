package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class ByteSubject<T> extends AbstractComparableNumber<ByteSubject<T>, T, Byte> {

//    private static final Byte ZERO = 0;
//    private static final Byte ONE = 1;

    public ByteSubject(PropertyRule<T, Byte> rule) {
        super(ByteSubject.class, rule);
    }

//    @Override
//    public ByteSubject<T> isZero() {
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ByteSubject<T> isNotZero() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ByteSubject<T> isOne() {
//        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public ByteSubject<T> isNotOne() {
//        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(ONE));
//        return myself;
//    }
//
//    @Override
//    public ByteSubject<T> isPositive() {
//        rule.addConstraint(new IsGreaterThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ByteSubject<T> isNotPositive() {
//        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ByteSubject<T> isNegative() {
//        rule.addConstraint(new IsLessThanConstraint<>(ZERO));
//        return myself;
//    }
//
//    @Override
//    public ByteSubject<T> isNotNegative() {
//        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(ZERO));
//        return myself;
//    }

    @Override
    protected Byte zero() {
        return 0;
    }

    @Override
    protected Byte one() {
        return 1;
    }
}
