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
    public LongArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new HasSameSizeAsLongArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLengthGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLengthGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLengthLessThan(int boundary) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLengthLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLengthBetween(int min, int max) {
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLengthSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsLongArrayConstraint<>(other));
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLengthSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsLongArrayConstraint<>(other));
        return myself;
    }
}
