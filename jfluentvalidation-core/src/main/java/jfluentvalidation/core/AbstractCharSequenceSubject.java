package jfluentvalidation.core;

import jfluentvalidation.constraints.charsequence.CharSequenceConstraints;
import jfluentvalidation.constraints.charsequence.ContainsConstraint;
import jfluentvalidation.constraints.charsequence.ContainsIgnoreCaseConstraint;
import jfluentvalidation.constraints.charsequence.ContainsOnlyDigitsConstraint;
import jfluentvalidation.constraints.charsequence.ContainsOnlyWhitespacesConstraint;
import jfluentvalidation.constraints.charsequence.ContainsWhitespacesConstraint;
import jfluentvalidation.constraints.charsequence.DoesNotContainAnyWhitespacesConstraint;
import jfluentvalidation.constraints.charsequence.HasLengthBetweenConstraint;
import jfluentvalidation.constraints.charsequence.HasLengthConstraint;
import jfluentvalidation.constraints.charsequence.HasLengthGreaterThanConstraint;
import jfluentvalidation.constraints.charsequence.HasLengthGreaterThanOrEqualToConstraint;
import jfluentvalidation.constraints.charsequence.HasLengthLessThanConstraint;
import jfluentvalidation.constraints.charsequence.HasLengthLessThanOrEqualToConstraint;
import jfluentvalidation.constraints.charsequence.HasSameLengthAsConstraint;
import jfluentvalidation.constraints.charsequence.IsBlankConstraint;
import jfluentvalidation.constraints.charsequence.IsEmailConstraint;
import jfluentvalidation.constraints.charsequence.IsEmptyConstraint;
import jfluentvalidation.constraints.charsequence.IsEqualToIgnoringCaseConstraint;
import jfluentvalidation.constraints.charsequence.IsEqualToIgnoringWhitespaceConstraint;
import jfluentvalidation.constraints.charsequence.IsEqualToNormalizingWhitespaceConstraint;
import jfluentvalidation.constraints.charsequence.IsNotEmptyConstraint;
import jfluentvalidation.constraints.charsequence.IsNullOrEmptyConstraint;
import jfluentvalidation.constraints.charsequence.LengthConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.regex.Pattern;

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}.
 */
public abstract class AbstractCharSequenceSubject<S extends AbstractCharSequenceSubject<S, T, A>, T, A extends CharSequence>
    extends Subject<S, T, A>
    implements ReadableSequence<S, T, A> {

    public AbstractCharSequenceSubject(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
    }

    @Override
    public void isNullOrEmpty() {
        rule.addConstraint(new IsNullOrEmptyConstraint<>());
    }

    @Override
    public void isEmpty() {
        rule.addConstraint(new IsEmptyConstraint<>());
    }

    @Override
    public S isNotEmpty() {
        rule.addConstraint(new IsNotEmptyConstraint<>());
        return myself;
    }

    @Override
    public S isBlank() {
        rule.addConstraint(new IsBlankConstraint<>());
        return myself;
    }

    @Override
    public S isNotBlank() {
        return myself;
    }

    @Override
    public S containsWhitespaces() {
        rule.addConstraint(new ContainsWhitespacesConstraint<>());
        return myself;
    }

    @Override
    public S containsOnlyWhitespaces() {
        rule.addConstraint(new ContainsOnlyWhitespacesConstraint<>());
        return myself;
    }

    @Override
    public S doesNotContainAnyWhitespaces() {
        rule.addConstraint(new DoesNotContainAnyWhitespacesConstraint<>());
        return myself;
    }

    @Override
    public S doesNotContainOnlyWhitespaces() {
        return myself;
    }

    // TODO: decide on appropriate method names for length
    public S length(int minLength, int maxLength) {
        rule.addConstraint(new LengthConstraint<>(minLength, maxLength));
        return myself;
    }

    @Override
    public S hasLength(int expected) {
        rule.addConstraint(new HasLengthConstraint<>(expected));
        return myself;
    }

    @Override
    public S hasLengthLessThan(int expected) {
        rule.addConstraint(new HasLengthLessThanConstraint<>(expected));
        return myself;
    }

    @Override
    public S hasLengthLessThanOrEqualTo(int expected) {
        rule.addConstraint(new HasLengthLessThanOrEqualToConstraint<>(expected));
        return myself;
    }

    @Override
    public S hasLengthGreaterThan(int expected) {
        rule.addConstraint(new HasLengthGreaterThanConstraint<>(expected));
        return myself;
    }

    @Override
    public S hasLengthGreaterThanOrEqualTo(int expected) {
        rule.addConstraint(new HasLengthGreaterThanOrEqualToConstraint<>(expected));
        return myself;
    }

    @Override
    public S hasLengthBetween(int min, int max) {
        // TODO: should we include start and end? whats the common pattern?
        rule.addConstraint(new HasLengthBetweenConstraint<>(min, max, true, true));
        return myself;
    }

    @Override
    public S hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new HasLengthBetweenConstraint<>(min, max, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public S hasSameLengthAs(CharSequence other) {
        rule.addConstraint(new HasSameLengthAsConstraint<>(other));
        return myself;
    }

    @Override
    public S isEqualToIgnoringCase(CharSequence expected) {
        rule.addConstraint(new IsEqualToIgnoringCaseConstraint<>(expected));
        return myself;
    }

    @Override
    public S isNotEqualToIgnoringCase(CharSequence expected) {
        return myself;
    }

    @Override
    public S containsOnlyDigits() {
        rule.addConstraint(new ContainsOnlyDigitsConstraint<>());
        return myself;
    }

    @Override
    public S containsOnlyOnce(CharSequence sequence) {
        return myself;
    }

    @Override
    public S contains(CharSequence... values) {
        rule.addConstraint(new ContainsConstraint<>(values));
        return myself;
    }

    @Override
    public S contains(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public S containsIgnoreCase(CharSequence sequence) {
        rule.addConstraint(new ContainsIgnoreCaseConstraint<>(sequence));
        return myself;
    }

    @Override
    public S doesNotContain(CharSequence... values) {
        return myself;
    }

    @Override
    public S doesNotContain(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public S doesNotContainPattern(CharSequence pattern) {
        return myself;
    }

    @Override
    public S doesNotContainPattern(Pattern pattern) {
        return myself;
    }

    @Override
    public S startsWith(CharSequence prefix) {
        return startsWith(prefix, 0);
    }

    @Override
    public S startsWith(CharSequence prefix, int offset) {
        rule.addConstraint(CharSequenceConstraints.startsWith(prefix, offset));
        return myself;
    }

    @Override
    public S doesNotStartWith(CharSequence prefix) {
        return myself;
    }

    @Override
    public S endsWith(CharSequence suffix) {
        rule.addConstraint(CharSequenceConstraints.endsWith(suffix));
        return myself;
    }

    @Override
    public S doesNotEndWith(CharSequence suffix) {
        // TODO: implement
        return myself;
    }

    @Override
    public S matches(CharSequence regex) {
        rule.addConstraint(CharSequenceConstraints.matches(regex));
        return myself;
    }

    @Override
    public S doesNotMatch(CharSequence regex) {
        // TODO: implement
        return myself;
    }

    @Override
    public S matches(Pattern pattern) {
        rule.addConstraint(CharSequenceConstraints.matches(pattern));
        return myself;
    }

    @Override
    public S doesNotMatch(Pattern pattern) {
        return myself;
    }

    @Override
    public S isEqualToIgnoringWhitespace(CharSequence expected) {
        rule.addConstraint(new IsEqualToIgnoringWhitespaceConstraint<>(expected));
        return myself;
    }

    @Override
    public S isNotEqualToIgnoringWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public S isEqualToNormalizingWhitespace(CharSequence expected) {
        rule.addConstraint(new IsEqualToNormalizingWhitespaceConstraint<>(expected));
        return myself;
    }

    @Override
    public S isNotEqualToNormalizingWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public S isSubstringOf(CharSequence sequence) {
        rule.addConstraint(CharSequenceConstraints.isSubstringOf(sequence));
        return myself;
    }

    @Override
    public S containsPattern(CharSequence regex) {
        rule.addConstraint(CharSequenceConstraints.containsPattern(regex));
        return myself;
    }

    @Override
    public S containsPattern(Pattern pattern) {
        rule.addConstraint(CharSequenceConstraints.containsPattern(pattern));
        return myself;
    }

    @Override
    public S isLowerCase() {
        rule.addConstraint(CharSequenceConstraints.isLowerCase());
        return myself;
    }

    @Override
    public S isUpperCase() {
        rule.addConstraint(CharSequenceConstraints.isUpperCase());
        return myself;
    }

    @Override
    public S isEmail() {
        rule.addConstraint(new IsEmailConstraint<>());
        return myself;
    }
}
