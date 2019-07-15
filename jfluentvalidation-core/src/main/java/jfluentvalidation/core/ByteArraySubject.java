package jfluentvalidation.core;

import jfluentvalidation.constraints.array.length.ByteArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.ByteArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ByteArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.ByteArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyByteArrayConstraint;
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
        rule.addConstraint(new ByteArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new ByteArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new ByteArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasLengthBetween(int min, int max) {
        rule.addConstraint(new ByteArrayBetweenLengthConstraint<>(min, max));
        return myself;
    }


    @Override
    public ByteArraySubject<T> hasSameLengthAs(Iterable<Byte> other) {
        rule.addConstraint(new ByteArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public ByteArraySubject<T> hasSameLengthAs(Byte[] other) {
        rule.addConstraint(new ByteArrayExactLengthConstraint<>(other));
        return myself;
    }
}
