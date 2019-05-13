package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyObjectArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsObjectArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 * @param <E>  the type of the element in the array
 */
public class ObjectArraySubject<T> extends AbstractArraySubject<ObjectArraySubject<T>, T, Object[], Object> {

    public ObjectArraySubject(PropertyRule<T, Object[]> rule) {
        super(ObjectArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public ObjectArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyObjectArrayConstraint<>());
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSize(int expected) {
        rule.addConstraint(new HasSameSizeAsObjectArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSizeGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSizeGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSizeLessThan(int boundary) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSizeLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSizeBetween(int min, int max) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSameSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsObjectArrayConstraint<>(other));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSameSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsObjectArrayConstraint<>(other));
        return myself;
    }
}
