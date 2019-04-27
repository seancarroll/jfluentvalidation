package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// QUESTION: Should we remove this? I think we can do everything just via ArraySubject
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
