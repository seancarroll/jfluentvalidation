package jfluentvalidation.core;

import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.array.contains.ContainsCharConstraint;
import jfluentvalidation.constraints.array.containsall.ContainsAllCharConstraint;
import jfluentvalidation.constraints.array.containsany.ContainsAnyCharConstraint;
import jfluentvalidation.constraints.array.containsexactly.ContainsExactlyConstraint;
import jfluentvalidation.constraints.array.containsnone.ContainsNoneCharConstraint;
import jfluentvalidation.constraints.array.empty.IsEmptyCharArrayConstraint;
import jfluentvalidation.constraints.array.isnotnullorempty.IsNotNullOrEmptyCharArrayConstraint;
import jfluentvalidation.constraints.array.length.CharArrayBetweenLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayMaximumLengthConstraint;
import jfluentvalidation.constraints.array.length.CharArrayMinimumLengthConstraint;
import jfluentvalidation.constraints.array.notempty.IsNotEmptyCharArrayConstraint;
import jfluentvalidation.constraints.array.nullorempty.IsNullOrEmptyCharArrayConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Arrays;

/**
 * Constraints for {@code char[]} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class CharArraySubject<T> extends AbstractArraySubject<CharArraySubject<T>, T, char[], Character> {

    public CharArraySubject(PropertyRule<T, char[]> rule) {
        super(CharArraySubject.class, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyCharArrayConstraint<>());
    }

    @Override
    public void isNotNullOrEmpty() {
        rule.addConstraint(new IsNotNullOrEmptyCharArrayConstraint<>());
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
    public CharArraySubject<T> containsAny(Character... values) {
        return containsAny(Arrays.asList(values));
    }

    @Override
    public CharArraySubject<T> containsAny(Iterable<Character> values) {
        rule.addConstraint(new ContainsAnyCharConstraint<>(values));
        return myself;
    }

    @Override
    public CharArraySubject<T> containsAll(Character... expected) {
        return containsAll(Arrays.asList(expected));
    }

    @Override
    public CharArraySubject<T> containsAll(Iterable<Character> expected) {
        rule.addConstraint(new ContainsAllCharConstraint<>(expected));
        return myself;
    }

    @Override
    public CharArraySubject<T> containsExactly(Character... expected) {
        return containsExactly(Arrays.asList(expected));
    }

    public CharArraySubject<T> containsExactly(char[] expected) {
        rule.addConstraint(new ContainsExactlyConstraint<>(Chars.asList(expected)));
        return myself;
    }

    @Override
    public CharArraySubject<T> containsExactly(Iterable<Character> expected) {
        rule.addConstraint(new ContainsExactlyConstraint<>(Lists.newArrayList(expected)));
        return myself;
    }

    @Override
    public CharArraySubject<T> containsNone(Character... values) {
        return containsNone(Arrays.asList(values));
    }

    @Override
    public CharArraySubject<T> containsNone(Iterable<Character> values) {
        rule.addConstraint(new ContainsNoneCharConstraint<>(values));
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

    @CanIgnoreReturnValue
    public CharArraySubject<T> hasSameLengthAs(char[] other) {
        rule.addConstraint(new CharArrayExactLengthConstraint<>(other));
        return myself;
    }
}
