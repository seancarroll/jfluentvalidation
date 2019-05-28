package jfluentvalidation.core;

import jfluentvalidation.constraints.array.length.ObjectArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.ObjectArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyObjectArrayConstraint;
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
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new ObjectArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new ObjectArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasLengthBetween(int min, int max) {
        rule.addConstraint(new ObjectArrayBetweenLengthConstraint<>(min, max));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSameLengthAs(Iterable<?> other) {
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public ObjectArraySubject<T> hasSameLengthAs(Object other) {
        rule.addConstraint(new ObjectArrayExactLengthConstraint<>(other));
        return myself;
    }
}
