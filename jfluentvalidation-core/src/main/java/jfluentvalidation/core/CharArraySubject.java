package jfluentvalidation.core;

import jfluentvalidation.constraints.array.length.CharArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyCharArrayConstraint;
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
        rule.addConstraint(new CharArrayExactLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public CharArraySubject<T> hasMinimumLength(int min) {
        rule.addConstraint(new CharArrayMinimumLengthConstraint<>(min));
        return myself;
    }

    @Override
    public CharArraySubject<T> hasMaximumLength(int max) {
        rule.addConstraint(new CharArrayMaximumLengthConstraint<>(max));
        return myself;
    }

    @Override
    public CharArraySubject<T> hasLengthBetween(int min, int max) {
        rule.addConstraint(new CharArrayBetweenLengthConstraint<>(min, max));
        return myself;
    }

    @Override
    public CharArraySubject<T> hasSameLengthAs(Iterable<Character> other) {
        rule.addConstraint(new CharArrayExactLengthConstraint<>(other));
        return myself;
    }

    @Override
    public CharArraySubject<T> hasSameLengthAs(Character[] other) {
        rule.addConstraint(new CharArrayExactLengthConstraint<>(other));
        return myself;
    }
}
