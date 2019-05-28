package jfluentvalidation.core;

import jfluentvalidation.constraints.array.length.IntArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.IntArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.IntArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.IntArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyIntArrayConstraint;
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
        rule.addConstraint(new IntArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new IntArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new IntArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasLengthBetween(int min, int max) {
        rule.addConstraint(new IntArrayBetweenLengthConstraint<>(min, max));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasSameLengthAs(Iterable<?> other) {
        rule.addConstraint(new IntArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasSameLengthAs(Object other) {
        rule.addConstraint(new IntArrayExactLengthConstraint<>(other));
        return myself;
    }
}
