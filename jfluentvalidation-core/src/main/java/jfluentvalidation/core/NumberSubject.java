package jfluentvalidation.core;

// TODO: what should this extend from? Do we need an abstract class?
// should it match java's type hierarchy? NumberSubject as an abstract class and Integer implementing Comparable?
public interface NumberSubject<S extends NumberSubject<S, A>, A extends Number> {

    S isZero();

    S isNotZero();

    S isOne();

    S isNotOne();

    S isPositive();

    S isNotPositive();

    S isNegative();

    S isNotNegative();

    S isBetween();

    // TODO: alternative name
    S isStrictlyBetween();

    // TODO: alternative name
    S isCloseTo();

    // TODO: alternative name
    S isNotCloseTo();

    // Comparator?
    // TODO: others? largerThan, lessThan, equal, lessThanOrEqual, greaterThanOrEqual

}
