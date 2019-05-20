package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyIntArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsIntArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 */
public class IntArraySubject<T> extends AbstractIntArraySubject<IntArraySubject<T>, T> {

    public IntArraySubject(PropertyRule<T, int[]> rule) {
        super(IntArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public IntArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyIntArrayConstraint<>());
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new HasSameSizeAsIntArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLengthGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLengthGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLengthLessThan(int boundary) {
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLengthLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLengthBetween(int min, int max) {
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLengthSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsIntArrayConstraint<>(other));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLengthSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsIntArrayConstraint<>(other));
        return myself;
    }
}
