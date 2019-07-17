package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

public interface ArraySubject<S extends ArraySubject<S, A, E>, A, E> {

    @CanIgnoreReturnValue
    void isNullOrEmpty();

    // TODO: add isNotNullOrEmpty()

    @CanIgnoreReturnValue
    void isEmpty();

    @CanIgnoreReturnValue
    S isNotEmpty();

    @CanIgnoreReturnValue
    S hasLength(int expected);

    @CanIgnoreReturnValue
    S contains(E element);

    @CanIgnoreReturnValue
    S hasMinimumLength(int min);

    @CanIgnoreReturnValue
    S hasMaximumLength(int max);

    @CanIgnoreReturnValue
    S hasLengthBetween(int min, int max);

//    S hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd);

    @CanIgnoreReturnValue
    S hasSameLengthAs(Iterable<E> other);

    @CanIgnoreReturnValue
    S hasSameLengthAs(E[] other);

}
