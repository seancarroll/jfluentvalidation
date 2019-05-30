package jfluentvalidation.core;

public interface ArraySubject<S extends ArraySubject<S, A, E>, A, E> {

    void isNullOrEmpty();

    void isEmpty();

    S isNotEmpty();

    S hasLength(int expected);

//    S contains(E item);

    S hasMinimumLength(int min);

    S hasMaximumLength(int max);

    S hasLengthBetween(int min, int max);

    S hasSameLengthAs(Iterable<?> other);

    S hasSameLengthAs(Object other);

}
