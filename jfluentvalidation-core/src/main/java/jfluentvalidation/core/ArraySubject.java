package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

public interface ArraySubject<S extends ArraySubject<S, A, E>, A, E> {

    @CanIgnoreReturnValue
    void isNullOrEmpty();

    @CanIgnoreReturnValue
    void isNotNullOrEmpty();

    @CanIgnoreReturnValue
    void isEmpty();

    @CanIgnoreReturnValue
    S isNotEmpty();

    @CanIgnoreReturnValue
    S hasLength(int expected);

    @CanIgnoreReturnValue
    S contains(E element);

    @CanIgnoreReturnValue
    S containsAnyOf(E... values);

    @CanIgnoreReturnValue
    S containsAnyOf(Iterable<E> values);

    @CanIgnoreReturnValue
    S containsAllOf(E... expected);

    @CanIgnoreReturnValue
    S containsAllOf(Iterable<E> expected);

    @CanIgnoreReturnValue
    S containsExactly(E... exactly);

    @CanIgnoreReturnValue
    S containsExactly(Iterable<E> expected);

    @CanIgnoreReturnValue
    S containsNone(E... values);

    @CanIgnoreReturnValue
    S containsNone(Iterable<E> values);

    @CanIgnoreReturnValue
    S hasMinimumLength(int min);

    @CanIgnoreReturnValue
    S hasMaximumLength(int max);

    @CanIgnoreReturnValue
    S hasLengthBetween(int min, int max);

    @CanIgnoreReturnValue
    S hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd);

    @CanIgnoreReturnValue
    S hasSameLengthAs(Iterable<E> other);

    @CanIgnoreReturnValue
    S hasSameLengthAs(E[] other);

}
