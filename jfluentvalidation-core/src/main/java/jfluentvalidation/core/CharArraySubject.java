package jfluentvalidation.core;

import jfluentvalidation.constraints.array.notempty.IsNotEmptyCharArrayConstraint;
import jfluentvalidation.constraints.array.sizeas.HasSameSizeAsCharArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

public class CharArraySubject<T> extends AbstractArraySubject<CharArraySubject<T>, T, char[], Character> {

    public CharArraySubject(PropertyRule<T, char[]> rule) {
        super(CharArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public CharArraySubject<T> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyCharArrayConstraint<>());
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLength(int expected) {
        rule.addConstraint(new HasSameSizeAsCharArrayConstraint<>(expected));
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLengthGreaterThan(int boundary) {
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLengthGreaterThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLengthLessThan(int boundary) {
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLengthLessThanOrEqualTo(int boundary) {
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLengthBetween(int min, int max) {
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLengthSizeAs(Iterable<?> other) {
        rule.addConstraint(new HasSameSizeAsCharArrayConstraint<>(other));
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLengthSizeAs(Object other) {
        rule.addConstraint(new HasSameSizeAsCharArrayConstraint<>(other));
        return myself;
    }
}
