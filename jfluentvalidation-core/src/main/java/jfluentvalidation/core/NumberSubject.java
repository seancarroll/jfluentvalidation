package jfluentvalidation.core;

// TODO: what should this extend from? Do we need an abstract class?
// should it match java's type hierarchy? NumberSubject as an abstract class and Integer implementing Comparable?

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <A>  the type of the actual object being tested by this {@code Subject}
 */
public interface NumberSubject<S extends NumberSubject<S, A>, A extends Number> {

    @CanIgnoreReturnValue
    S isZero();

    @CanIgnoreReturnValue
    S isNotZero();

    @CanIgnoreReturnValue
    S isOne();

    @CanIgnoreReturnValue
    S isNotOne();

    @CanIgnoreReturnValue
    S isPositive();

    @CanIgnoreReturnValue
    S isNotPositive();

    @CanIgnoreReturnValue
    S isNegative();

    @CanIgnoreReturnValue
    S isNotNegative();

    @CanIgnoreReturnValue
    S isCloseTo(A expected, A offset, boolean strict);

    // TODO: isCloseTo...percentage


    @CanIgnoreReturnValue
    S isNotCloseTo(A expected, A offset, boolean strict);

    // TODO: isNotCloseTo...percentage
}
