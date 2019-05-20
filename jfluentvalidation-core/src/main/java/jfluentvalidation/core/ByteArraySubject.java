package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyByteArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsByteArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

// TODO: We could potentially have many different types of array
// what makes sense to include? assertj has the following:
// BooleanArray, CharArray, DoubleArray, FloatArray, IntArray, ObjectArray, ShortArray
// this could extend an AbstractArraySubject

/**
 *
 * @param <T>  the type of the instance
 */
public class ByteArraySubject<T> extends AbstractByteArraySubject<ByteArraySubject<T>, T> {

    public ByteArraySubject(PropertyRule<T, byte[]> rule) {
        super(ByteArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {
//        IsEmptyConstraint<T, byte[]> c = new IsEmptyConstraint<>();
//        rule.addConstraint(c);
    }

    @Override
    public ByteArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyByteArrayConstraint<>());
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new HasSameSizeAsByteArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLengthGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLengthGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLengthLessThan(int boundary) {
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLengthLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLengthBetween(int lowerBoundary, int higherBoundary) {
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLengthSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsByteArrayConstraint<>(other));
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLengthSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsByteArrayConstraint<>(other));
        return myself;
    }
}
