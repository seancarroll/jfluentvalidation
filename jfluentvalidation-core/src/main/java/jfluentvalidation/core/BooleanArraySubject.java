package jfluentvalidation.core;

import jfluentvalidation.constraints.array.contains.ContainsBooleanConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyBooleanArrayConstraint;
import jfluentvalidation.constraints.array.length.ArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ArrayLengthConstraint;
import jfluentvalidation.constraints.array.length.BooleanArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.BooleanArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyBooleanArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyBooleanArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

public class BooleanArraySubject<T> extends AbstractArraySubject<BooleanArraySubject<T>, T, boolean[], Boolean> {

    public BooleanArraySubject(PropertyRule<T, boolean[]> rule) {
        super(BooleanArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyBooleanArrayConstraint<>());
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
        rule.addConstraint(new ArrayLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSameLengthAs(Iterable<Boolean> other) {
        rule.addConstraint(new ArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSameLengthAs(Boolean[] other) {
        rule.addConstraint(new ArrayExactLengthConstraint<>(other));
        return myself;
    }
}
