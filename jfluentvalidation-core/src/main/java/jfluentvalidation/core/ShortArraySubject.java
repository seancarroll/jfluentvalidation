package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyShortArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsShortArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 */
public class ShortArraySubject<T> extends AbstractArraySubject<ShortArraySubject<T>, T, short[], Short> {

    public ShortArraySubject(PropertyRule<T, short[]> rule) {
        super(ShortArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public ShortArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyShortArrayConstraint<>());
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSize(int expected) {
        rule.addConstraint(new HasSameSizeAsShortArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSizeGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSizeGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSizeLessThan(int boundary) {
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSizeLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSizeBetween(int min, int max) {
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSameSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsShortArrayConstraint<>(other));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSameSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsShortArrayConstraint<>(other));
        return myself;
    }
}
