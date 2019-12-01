package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsDoubleConstraint;
import jfluentvalidation.constraints.array.containsall.ContainsAllDoubleConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyDoubleConstraint;
import jfluentvalidation.constraints.array.containsnone.ContainsNoneDoubleConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyDoubleArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyDoubleArrayConstraint;
import jfluentvalidation.constraints.array.length.DoubleArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.DoubleArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.DoubleArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.DoubleArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyDoubleArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyDoubleArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

/**
 * Constraints for {@code double[]} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class DoubleArraySubject<T> extends AbstractArraySubject<DoubleArraySubject<T>, T, double[], Double> {

    public DoubleArraySubject(PropertyRule<T, double[]> rule) {
        super(DoubleArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyDoubleArrayConstraint<>());
    }

    @Override
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyDoubleArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyDoubleArrayConstraint<>());
    }

    @Override
    public DoubleArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyDoubleArrayConstraint<>());
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new DoubleArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> contains(Double element) {
        rule.addConstraint(new ContainsDoubleConstraint<>(element));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> containsAny(Double... values) {
        return containsAny(Arrays.asList(values));
    }

    @Override
    public DoubleArraySubject<T> containsAny(Iterable<Double> values) {
        rule.addConstraint(new ContainsAnyDoubleConstraint<>(values));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> containsAll(Double... expected) {
        return containsAll(Arrays.asList(expected));
    }

    @Override
    public DoubleArraySubject<T> containsAll(Iterable<Double> expected) {
        rule.addConstraint(new ContainsAllDoubleConstraint<>(expected));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> containsExactly(Double... exactly) {
        return null;
    }

    @Override
    public DoubleArraySubject<T> containsExactly(Iterable<Double> expected) {
        return null;
    }

    @Override
    public DoubleArraySubject<T> containsNone(Double... values) {
        return containsNone(Arrays.asList(values));
    }

    @Override
    public DoubleArraySubject<T> containsNone(Iterable<Double> values) {
        rule.addConstraint(new ContainsNoneDoubleConstraint<>(values));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new DoubleArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new DoubleArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasLengthBetween(int min, int max) {
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public DoubleArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new DoubleArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasSameLengthAs(Iterable<Double> other) {
        rule.addConstraint(new DoubleArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public DoubleArraySubject<T> hasSameLengthAs(Double[] other) {
        rule.addConstraint(new DoubleArrayExactLengthConstraint<>(other));
        return myself;
    }

    @CanIgnoreReturnValue
    public DoubleArraySubject<T> hasSameLengthAs(double[] other) {
        rule.addConstraint(new DoubleArrayExactLengthConstraint<>(other));
        return myself;
    }

}
