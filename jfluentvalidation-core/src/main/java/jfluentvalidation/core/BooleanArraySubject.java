package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyBooleanArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsBooleanArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

public class BooleanArraySubject<T> extends AbstractArraySubject<BooleanArraySubject<T>, T, boolean[], Boolean> {

    public BooleanArraySubject(PropertyRule<T, boolean[]> rule) {
        super(BooleanArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public BooleanArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyBooleanArrayConstraint<>());
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSize(int expected) {
        rule.addConstraint(new HasSameSizeAsBooleanArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSizeGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSizeGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSizeLessThan(int boundary) {
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSizeLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSizeBetween(int min, int max) {
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSameSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsBooleanArrayConstraint<>(other));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSameSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsBooleanArrayConstraint<>(other));
        return myself;
    }
}
