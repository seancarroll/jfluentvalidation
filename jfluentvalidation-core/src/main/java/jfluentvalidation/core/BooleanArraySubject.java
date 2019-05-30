package jfluentvalidation.core;

import jfluentvalidation.constraints.array.length.ArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.ArrayLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyBooleanArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

public class BooleanArraySubject<T> extends AbstractArraySubject<BooleanArraySubject<T>, T, boolean[], Boolean> {

    public BooleanArraySubject(PropertyRule<T, boolean[]> rule) {
        super(BooleanArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

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

//    public BooleanArraySubject<T> contains(Boolean item) {
//        rule.addConstraint(new ContainsConstraint<>(item));
//        return myself;
//    }

    @Override
    public BooleanArraySubject<T> hasMinimumLength(int min) {
        return null;
    }

    @Override
    public BooleanArraySubject<T> hasMaximumLength(int max) {
        return null;
    }

    @Override
    public BooleanArraySubject<T> hasLengthBetween(int min, int max) {
        rule.addConstraint(new ArrayLengthConstraint<>(min, max));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSameLengthAs(Iterable<?> other) {
        rule.addConstraint(new ArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public BooleanArraySubject<T> hasSameLengthAs(Object other) {
        rule.addConstraint(new ArrayExactLengthConstraint<>(other));
        return myself;
    }
}
