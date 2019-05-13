package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyLongArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsLongArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 */
public class LongArraySubject<T> extends AbstractArraySubject<LongArraySubject<T>, T, long[], Long> {

    public LongArraySubject(PropertyRule<T, long[]> rule) {
        super(LongArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public LongArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyLongArrayConstraint<>());
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSize(int expected) {
        rule.addConstraint(new HasSameSizeAsLongArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSizeGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSizeGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSizeLessThan(int boundary) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSizeLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSizeBetween(int min, int max) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSameSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsLongArrayConstraint<>(other));
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSameSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsLongArrayConstraint<>(other));
        return myself;
    }
}
