package jfluentvalidation.core;

public interface ArraySubject<S extends ArraySubject<S, E>, E> {

    void isNullOrEmpty();

    void isEmpty();

    S isNotEmpty();

    S hasLength(int expected);

    S hasMinimumLength(int min);

    S hasMaximumLength(int max);

    S hasLengthBetween(int min, int max);

    S hasSameLengthAs(Iterable<?> other);

    S hasSameLengthAs(Object other);

}
