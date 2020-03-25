package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsBooleanConstraint;
import jfluentvalidation.constraints.array.containsall.ContainsAllBooleanConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyBooleanConstraint;
import jfluentvalidation.constraints.array.containsexactly.ContainsExactlyConstraint;
import jfluentvalidation.constraints.array.containsnone.ContainsNoneBooleanConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyBooleanArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyBoolenArrayConstraint;
import jfluentvalidation.constraints.array.length.ArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.BooleanArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.BooleanArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.BooleanArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.BooleanArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyBooleanArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyBooleanArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

/**
 * Constraints for {@code boolean[]} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class BooleanArraySubject<T> extends AbstractArraySubject<BooleanArraySubject<T>, T, boolean[], Boolean> {

    public BooleanArraySubject(PropertyRule<T, boolean[]> rule) {
        super(BooleanArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyBooleanArrayConstraint<>());
    }

    @Override
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyBoolenArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyBooleanArrayConstraint<>());
    }

    @Override
    public BooleanArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyBooleanArrayConstraint<>());
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new ArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> contains(Boolean element) {
        rule.addConstraint(new ContainsBooleanConstraint<>(element));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> containsAny(Boolean... values) {
        return containsAny(Arrays.asList(values));
    }

    @Override
    public BooleanArraySubject<T> containsAny(Iterable<Boolean> values) {
        rule.addConstraint(new ContainsAnyBooleanConstraint<>(values));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> containsAll(Boolean... expected) {
        return containsAll(Arrays.asList(expected));
    }

    @Override
    public BooleanArraySubject<T> containsAll(Iterable<Boolean> expected) {
        rule.addConstraint(new ContainsAllBooleanConstraint<>(expected));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> containsExactly(Boolean... expected) {
        return containsExactly(Arrays.asList(expected));
    }

    @Override
    public BooleanArraySubject<T> containsExactly(Iterable<Boolean> expected) {
        rule.addConstraint(new ContainsExactlyConstraint<>(expected));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> containsNone(Boolean... values) {
        return containsNone(Arrays.asList(values));
    }

    @Override
    public BooleanArraySubject<T> containsNone(Iterable<Boolean> values) {
        rule.addConstraint(new ContainsNoneBooleanConstraint<>(values));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new BooleanArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new BooleanArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasLengthBetween(int min, int max) {
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public BooleanArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new BooleanArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSameLengthAs(Iterable<Boolean> other) {
        rule.addConstraint(new BooleanArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSameLengthAs(Boolean[] other) {
        rule.addConstraint(new BooleanArrayExactLengthConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public BooleanArraySubject<T> hasSameLengthAs(boolean[] other) {
        rule.addConstraint(new BooleanArrayExactLengthConstraint<>(other));
        return myself;
    }
}
