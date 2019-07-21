package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsShortConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyShortArrayConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyShortArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyShortArrayConstraint;
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
        rule.addConstraint(new IsNullOrEmptyShortArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyShortArrayConstraint<>());
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
    public ShortArraySubject<T> contains(Short element) {
        rule.addConstraint(new ContainsShortConstraint<>(element));
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
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public ShortArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new ShortArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSameLengthAs(Iterable<Short> other) {
        rule.addConstraint(new ShortArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public ShortArraySubject<T> hasSameLengthAs(Short[] other) {
        rule.addConstraint(new ShortArrayExactLengthConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public ShortArraySubject<T> hasSameLengthAs(short[] other) {
        rule.addConstraint(new ShortArrayExactLengthConstraint<>(other));
        return myself;
    }
}
