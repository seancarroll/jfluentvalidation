package jfluentvalidation.core;

import jfluentvalidation.constraints.array.contains.ContainsFloatConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyFloatArrayConstraint;
import jfluentvalidation.constraints.array.length.FloatArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.FloatArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.FloatArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.FloatArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyFloatArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyFloatArrayConstraint;
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
        rule.addConstraint(new IsNullOrEmptyFloatArrayConstraint<>());
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
        rule.addConstraint(new FloatArrayBetweenLengthConstraint<>(min, max));
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

    @Override
    public FloatArraySubject<T> contains(Float value) {
        rule.addConstraint(new ContainsFloatConstraint<>(value));
        return myself;
    }
}
