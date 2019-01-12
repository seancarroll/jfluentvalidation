package jfluentvalidation.core;

public class IntegerSubject implements NumberSubject<IntegerSubject, Integer>  {

//    public IntegerSubject(Integer actual) {
//        super(actual, IntegerSubject.class);
//    }
//
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
        return null;
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


}
