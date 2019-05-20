package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyObjectArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsObjectArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
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
    public ObjectArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new HasSameSizeAsObjectArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthLessThan(int boundary) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthBetween(int min, int max) {
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsObjectArrayConstraint<>(other));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsObjectArrayConstraint<>(other));
        return myself;
    }
}
