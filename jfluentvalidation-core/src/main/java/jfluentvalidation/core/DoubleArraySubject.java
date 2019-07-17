package jfluentvalidation.core;

import jfluentvalidation.constraints.array.contains.ContainsDoubleConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyDoubleArrayConstraint;
import jfluentvalidation.constraints.array.length.DoubleArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.DoubleArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.DoubleArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.DoubleArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyDoubleArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 */
public class DoubleArraySubject<T> extends AbstractArraySubject<DoubleArraySubject<T>, T, double[], Double> {

    public DoubleArraySubject(PropertyRule<T, double[]> rule) {
        super(DoubleArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

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
        rule.addConstraint(new DoubleArrayBetweenLengthConstraint<>(min, max));
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

}
