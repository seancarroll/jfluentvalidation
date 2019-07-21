package jfluentvalidation.core;

import jfluentvalidation.constraints.array.contains.ContainsCharConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyCharArrayConstraint;
import jfluentvalidation.constraints.array.length.CharArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyCharArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyCharArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

public class CharArraySubject<T> extends AbstractArraySubject<CharArraySubject<T>, T, char[], Character> {

    public CharArraySubject(PropertyRule<T, char[]> rule) {
        super(CharArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyCharArrayConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyCharArrayConstraint<>());
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
    public CharArraySubject<T> contains(Character element) {
        rule.addConstraint(new ContainsCharConstraint<>(element));
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
        return hasLengthBetween(min, max, true, true);
    }

    @Override
    public CharArraySubject<T> hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new CharArrayBetweenLengthConstraint<>(min, max, inclusiveStart, inclusiveEnd));
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
