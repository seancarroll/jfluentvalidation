package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyDoubleArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsDoubleArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 */
public class DoubleArraySubject<T> extends AbstractArraySubject<DoubleArraySubject<T>, T, double[], Double> {

    public DoubleArraySubject(PropertyRule<T, double[]> rule) {
        super(DoubleArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public DoubleArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyDoubleArrayConstraint<>());
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new HasSameSizeAsDoubleArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLengthGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLengthGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLengthLessThan(int boundary) {
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLengthLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLengthBetween(int min, int max) {
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLengthSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsDoubleArrayConstraint<>(other));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLengthSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsDoubleArrayConstraint<>(other));
        return myself;
    }

}
