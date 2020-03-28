package jfluentvalidation.core;

import com.google.common.collect.Lists;
import com.google.common.primitives.Shorts;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsShortConstraint;
import jfluentvalidation.constraints.array.containsall.ContainsAllShortConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyShortConstraint;
import jfluentvalidation.constraints.array.containsexactly.ContainsExactlyConstraint;
import jfluentvalidation.constraints.array.containsnone.ContainsNoneShortConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyShortArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyShortArrayConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.ShortArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyShortArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyShortArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

/**
 * Constraints for {@code short[]} subjects.
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
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyShortArrayConstraint<>());
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
    public ShortArraySubject<T> containsAny(Short... values) {
        return containsAny(Arrays.asList(values));
    }

    @Override
    public ShortArraySubject<T> containsAny(Iterable<Short> values) {
        rule.addConstraint(new ContainsAnyShortConstraint<>(values));
        return myself;
    }

    @Override
    public ShortArraySubject<T> containsAll(Short... expected) {
        return containsAll(Arrays.asList(expected));
    }

    @Override
    public ShortArraySubject<T> containsAll(Iterable<Short> expected) {
        rule.addConstraint(new ContainsAllShortConstraint<>(expected));
        return myself;
    }

    @Override
    public ShortArraySubject<T> containsExactly(Short... expected) {
        rule.addConstraint(new ContainsExactlyConstraint<>(Arrays.asList(expected)));
        return myself;
    }

    public ShortArraySubject<T> containsExactly(short[] expected) {
        rule.addConstraint(new ContainsExactlyConstraint<>(Shorts.asList(expected)));
        return myself;
    }

    @Override
    public ShortArraySubject<T> containsExactly(Iterable<Short> expected) {
        rule.addConstraint(new ContainsExactlyConstraint<>(Lists.newArrayList(expected)));
        return myself;
    }

    @Override
    public ShortArraySubject<T> containsNone(Short... values) {
        return containsNone(Arrays.asList(values));
    }

    @Override
    public ShortArraySubject<T> containsNone(Iterable<Short> values) {
        rule.addConstraint(new ContainsNoneShortConstraint<>(values));
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
