package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsIntConstraint;
import jfluentvalidation.constraints.array.containsallof.ContainsAllOfIntConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyIntConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyIntArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyIntArrayConstraint;
import jfluentvalidation.constraints.array.length.IntArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.IntArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.IntArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.IntArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyIntArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyIntArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

/**
 * Constraints for {@code int[]} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class IntArraySubject<T> extends AbstractArraySubject<IntArraySubject<T>, T, int[], Integer> {

    public IntArraySubject(PropertyRule<T, int[]> rule) {
        super(IntArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyIntArrayConstraint<>());
    }

    @Override
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyIntArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyIntArrayConstraint<>());
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
    public IntArraySubject<T> contains(Integer element) {
        rule.addConstraint(new ContainsIntConstraint<>(element));
        return myself;
    }

    @Override
    public IntArraySubject<T> containsAnyOf(Integer... values) {
        return containsAnyOf(Arrays.asList(values));
    }

    @Override
    public IntArraySubject<T> containsAnyOf(Iterable<Integer> values) {
        rule.addConstraint(new ContainsAnyIntConstraint<>(values));
        return myself;
    }

    @Override
    public IntArraySubject<T> containsAllOf(Integer... expected) {
        return containsAllOf(Arrays.asList(expected));
    }

    @Override
    public IntArraySubject<T> containsAllOf(Iterable<Integer> expected) {
        rule.addConstraint(new ContainsAllOfIntConstraint<>(expected));
        return myself;
    }

    @Override
    public IntArraySubject<T> containsExactly(Integer... exactly) {
        return null;
    }

    @Override
    public IntArraySubject<T> containsExactly(Iterable<Integer> expected) {
        return null;
    }

    @Override
    public IntArraySubject<T> containsNone(Integer... values) {
        return null;
    }

    @Override
    public IntArraySubject<T> containsNone(Iterable<? super Integer> values) {
        return null;
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
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public IntArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IntArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasSameLengthAs(Iterable<Integer> other) {
        rule.addConstraint(new IntArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public IntArraySubject<T> hasSameLengthAs(Integer[] other) {
        rule.addConstraint(new IntArrayExactLengthConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public IntArraySubject<T> hasSameLengthAs(int[] other) {
        rule.addConstraint(new IntArrayExactLengthConstraint<>(other));
        return myself;
    }
}
