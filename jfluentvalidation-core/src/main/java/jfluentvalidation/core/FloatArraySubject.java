package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyFloatArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsFloatArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 */
public class FloatArraySubject<T> extends AbstractArraySubject<FloatArraySubject<T>, T, float[], Float> {

    public FloatArraySubject(PropertyRule<T, float[]> rule) {
        super(FloatArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public FloatArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyFloatArrayConstraint<>());
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new HasSameSizeAsFloatArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLengthGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLengthGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLengthLessThan(int boundary) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLengthLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLengthBetween(int min, int max) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLengthSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsFloatArrayConstraint<>(other));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasLengthSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsFloatArrayConstraint<>(other));
        return myself;
    }
}
