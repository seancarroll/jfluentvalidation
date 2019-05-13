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
    public FloatArraySubject<T> hasSize(int expected) {
        rule.addConstraint(new HasSameSizeAsFloatArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSizeGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSizeGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSizeLessThan(int boundary) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSizeLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSizeBetween(int min, int max) {
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSameSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsFloatArrayConstraint<>(other));
        return myself;
    }

    @Override
    public FloatArraySubject<T> hasSameSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsFloatArrayConstraint<>(other));
        return myself;
    }
}
