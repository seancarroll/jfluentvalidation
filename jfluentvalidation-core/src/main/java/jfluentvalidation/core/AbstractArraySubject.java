package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// QUESTION: Should we remove this? I think we can do everything just via ArraySubject

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}.
 */
public abstract class AbstractArraySubject<S extends AbstractArraySubject<S, T, A>, T, A> extends Subject<S, T, A> {

    public AbstractArraySubject(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
    }

    // void isNullOrEmpty();

    // void isEmpty();

    // SELF isNotEmpty();

    // SELF hasSize(int expected);

    // SELF hasSizeGreaterThan(int boundary);

    // SELF hasSizeGreaterThanOrEqualTo(int boundary);

    // SELF hasSizeLessThan(int boundary);

    // SELF hasSizeLessThanOrEqualTo(int boundary);

    // SELF hasSizeBetween(int lowerBoundary, int higherBoundary);

    // SELF hasSameSizeAs(Iterable<?> other);

    // SELF hasSameSizeAs(Object array);

}
