package jfluentvalidation.core;

public interface ArraySubject<S extends ArraySubject<S, E>, E> {

    void isNullOrEmpty();

    void isEmpty();

    S isNotEmpty();

    S hasSize(int expected);

    S hasSizeGreaterThan(int boundary);

    S hasSizeGreaterThanOrEqualTo(int boundary);

    S hasSizeLessThan(int boundary);

    S hasSizeLessThanOrEqualTo(int boundary);

    S hasSizeBetween(int min, int max);

    S hasSameSizeAs(Iterable<?> other);

    S hasSameSizeAs(Object other);

}
