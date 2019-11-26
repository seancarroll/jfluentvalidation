package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsLongConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyLongConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyLongArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyLongArrayConstraint;
import jfluentvalidation.constraints.array.length.LongArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.LongArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.LongArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.LongArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyLongArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyLongArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

/**
 * Constraints for {@code long[]} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class LongArraySubject<T> extends AbstractArraySubject<LongArraySubject<T>, T, long[], Long> {

    public LongArraySubject(PropertyRule<T, long[]> rule) {
        super(LongArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyLongArrayConstraint<>());
    }

    @Override
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyLongArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyLongArrayConstraint<>());
    }

    @Override
    public LongArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyLongArrayConstraint<>());
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new LongArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public LongArraySubject<T> contains(Long element) {
        rule.addConstraint(new ContainsLongConstraint<>(element));
        return myself;
    }

    @Override
    public LongArraySubject<T> containsAnyOf(Long... values) {
        return containsAnyOf(Arrays.asList(values));
    }

    @Override
    public LongArraySubject<T> containsAnyOf(Iterable<Long> values) {
        rule.addConstraint(new ContainsAnyLongConstraint<>(values));
        return myself;
    }

    @Override
    public LongArraySubject<T> containsAllOf(Long... expected) {
        return null;
    }

    @Override
    public LongArraySubject<T> containsAllOf(Iterable<Long> expected) {
        return null;
    }

    @Override
    public LongArraySubject<T> containsExactly(Long... exactly) {
        return null;
    }

    @Override
    public LongArraySubject<T> containsExactly(Iterable<Long> expected) {
        return null;
    }

    @Override
    public LongArraySubject<T> containsNone(Long... values) {
        return null;
    }

    @Override
    public LongArraySubject<T> containsNone(Iterable<? super Long> values) {
        return null;
    }

    @Override
    public LongArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new LongArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public LongArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new LongArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public LongArraySubject<T> hasLengthBetween(int min, int max) {
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public LongArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new LongArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSameLengthAs(Iterable<Long> other) {
        rule.addConstraint(new LongArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public LongArraySubject<T> hasSameLengthAs(Long[] other) {
        rule.addConstraint(new LongArrayExactLengthConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public LongArraySubject<T> hasSameLengthAs(long[] other) {
        rule.addConstraint(new LongArrayExactLengthConstraint<>(other));
        return myself;
    }
}
