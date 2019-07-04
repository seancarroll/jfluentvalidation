package jfluentvalidation.core;

public interface ArraySubject<S extends ArraySubject<S, A, E>, A, E> {

    void isNullOrEmpty();

    // TODO: add isNotNullOrEmpty()

    void isEmpty();

    S isNotEmpty();

    S hasLength(int expected);

//    S contains(E item);

    S hasMinimumLength(int min);

    S hasMaximumLength(int max);

    S hasLengthBetween(int min, int max);

//    S hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd);

    S hasSameLengthAs(Iterable<?> other);

    S hasSameLengthAs(Object other);

}
