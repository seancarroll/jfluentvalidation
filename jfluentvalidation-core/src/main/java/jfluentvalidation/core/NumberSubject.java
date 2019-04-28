package jfluentvalidation.core;

// TODO: what should this extend from? Do we need an abstract class?
// should it match java's type hierarchy? NumberSubject as an abstract class and Integer implementing Comparable?

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <A>  the type of the actual object being tested by this {@code Subject}
 */
public interface NumberSubject<S extends NumberSubject<S, A>, A extends Number> {

    S isZero();

    S isNotZero();

    S isOne();

    S isNotOne();

    S isPositive();

    S isNotPositive();

    S isNegative();

    S isNotNegative();

    // TODO: alternative name
    S isCloseTo();

    // TODO: alternative name
    S isNotCloseTo();
}
