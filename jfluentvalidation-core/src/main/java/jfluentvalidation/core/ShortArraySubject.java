package jfluentvalidation.core;

import jfluentvalidation.constraints.array.length.ShortArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyShortArrayConstraint;
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
    public ShortArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new ShortArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new ShortArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new ShortArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasLengthBetween(int min, int max) {
        rule.addConstraint(new ShortArrayBetweenLengthConstraint<>(min, max));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSameLengthAs(Iterable<?> other) {
        rule.addConstraint(new ShortArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSameLengthAs(Object other) {
        rule.addConstraint(new ShortArrayExactLengthConstraint<>(other));
        return myself;
    }
}
