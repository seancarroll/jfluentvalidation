package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsFloatConstraint;
import jfluentvalidation.constraints.array.containsall.ContainsAllFloatConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyFloatConstraint;
import jfluentvalidation.constraints.array.containsnone.ContainsNoneFloatConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyFloatArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyFloatArrayConstraint;
import jfluentvalidation.constraints.array.length.FloatArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.FloatArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.FloatArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.FloatArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyFloatArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyFloatArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

/**
 * Constraints for {@code float[]} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class FloatArraySubject<T> extends AbstractArraySubject<FloatArraySubject<T>, T, float[], Float> {

    public FloatArraySubject(PropertyRule<T, float[]> rule) {
        super(FloatArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyFloatArrayConstraint<>());
    }

    @Override
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyFloatArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyFloatArrayConstraint<>());
    }

    @Override
    public FloatArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyFloatArrayConstraint<>());
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new FloatArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new FloatArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new FloatArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLengthBetween(int min, int max) {
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public FloatArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new FloatArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSameLengthAs(Iterable<Float> other) {
        rule.addConstraint(new FloatArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSameLengthAs(Float[] other) {
        rule.addConstraint(new FloatArrayExactLengthConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public FloatArraySubject<T> hasSameLengthAs(float[] other) {
        rule.addConstraint(new FloatArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public FloatArraySubject<T> contains(Float value) {
        rule.addConstraint(new ContainsFloatConstraint<>(value));
        return myself;
    }

    @Override
    public FloatArraySubject<T> containsAny(Float... values) {
        return containsAny(Arrays.asList(values));
    }

    @Override
    public FloatArraySubject<T> containsAny(Iterable<Float> values) {
        rule.addConstraint(new ContainsAnyFloatConstraint<>(values));
        return myself;
    }

    @Override
    public FloatArraySubject<T> containsAll(Float... expected) {
        return containsAll(Arrays.asList(expected));
    }

    @Override
    public FloatArraySubject<T> containsAll(Iterable<Float> expected) {
        rule.addConstraint(new ContainsAllFloatConstraint<>(expected));
        return myself;
    }

    @Override
    public FloatArraySubject<T> containsExactly(Float... exactly) {
        // TODO: implement
        return null;
    }

    @Override
    public FloatArraySubject<T> containsExactly(Iterable<Float> expected) {
        // TODO: implement
        return null;
    }

    @Override
    public FloatArraySubject<T> containsNone(Float... values) {
        return containsNone(Arrays.asList(values));
    }

    @Override
    public FloatArraySubject<T> containsNone(Iterable<Float> values) {
        rule.addConstraint(new ContainsNoneFloatConstraint<>(values));
        return myself;
    }
}
