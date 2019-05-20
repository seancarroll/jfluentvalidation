package jfluentvalidation.core;

public interface ArraySubject<S extends ArraySubject<S, E>, E> {

    void isNullOrEmpty();

    void isEmpty();

    S isNotEmpty();

    S hasLength(int expected);

    S hasLengthGreaterThan(int boundary);

    S hasLengthGreaterThanOrEqualTo(int boundary);

    S hasLengthLessThan(int boundary);

    S hasLengthLessThanOrEqualTo(int boundary);

    S hasLengthBetween(int min, int max);

    S hasLengthSizeAs(Iterable<?> other);

    S hasLengthSizeAs(Object other);

}
