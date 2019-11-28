package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsByteConstraint;
import jfluentvalidation.constraints.array.containsallof.ContainsAllOfByteConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyByteConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyByteArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyByteArrayConstraint;
import jfluentvalidation.constraints.array.length.ByteArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.ByteArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ByteArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.ByteArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyByteArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyByteArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

// TODO: We could potentially have many different types of array
// what makes sense to include? assertj has the following:
// BooleanArray, CharArray, DoubleArray, FloatArray, IntArray, ObjectArray, ShortArray
// this could extend an AbstractArraySubject

/**
 * Constraints for {@code byte[]} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class ByteArraySubject<T> extends AbstractArraySubject<ByteArraySubject<T>, T, byte[], Byte> {

    public ByteArraySubject(PropertyRule<T, byte[]> rule) {
        super(ByteArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyByteArrayConstraint<>());
    }

    @Override
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyByteArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyByteArrayConstraint<>());
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
    public ByteArraySubject<T> contains(Byte element) {
        rule.addConstraint(new ContainsByteConstraint<>(element));
        return myself;
    }

    @Override
    public ByteArraySubject<T> containsAnyOf(Byte... values) {
        return containsAnyOf(Arrays.asList(values));
    }

    @Override
    public ByteArraySubject<T> containsAnyOf(Iterable<Byte> values) {
        rule.addConstraint(new ContainsAnyByteConstraint<>(values));
        return myself;
    }

    @Override
    public ByteArraySubject<T> containsAllOf(Byte... expected) {
        return containsAllOf(Arrays.asList(expected));
    }

    @Override
    public ByteArraySubject<T> containsAllOf(Iterable<Byte> expected) {
        rule.addConstraint(new ContainsAllOfByteConstraint<>(expected));
        return myself;
    }

    @Override
    public ByteArraySubject<T> containsExactly(Byte... exactly) {
        return null;
    }

    @Override
    public ByteArraySubject<T> containsExactly(Iterable<Byte> expected) {
        return null;
    }

    @Override
    public ByteArraySubject<T> containsNone(Byte... values) {
        return null;
    }

    @Override
    public ByteArraySubject<T> containsNone(Iterable<? super Byte> values) {
        return null;
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
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public ByteArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new ByteArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
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

    @CanIgnoreReturnValue
    public ByteArraySubject<T> hasSameLengthAs(byte[] other) {
        rule.addConstraint(new ByteArrayExactLengthConstraint<>(other));
        return myself;
    }
}
