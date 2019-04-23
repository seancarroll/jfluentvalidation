package jfluentvalidation.core;

import jfluentvalidation.constraints.charsequence.IsEmptyConstraint;
import jfluentvalidation.constraints.charsequence.IsNotEmptyConstraint;
import jfluentvalidation.constraints.charsequence.LengthConstraint;
import jfluentvalidation.constraints.charsequence.StartsWithConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.regex.Pattern;

/**
 *
 * @param <S>
 * @param <A>
 */
public abstract class AbstractCharSequenceSubject<S extends AbstractCharSequenceSubject<S, T, A>, T, A extends CharSequence>
    extends Subject<S, T, A>
    implements ReadableSequence<S, T, A> {

    public AbstractCharSequenceSubject(Class<?> selfType, PropertyRule<T, A> rule) {
        super(selfType, rule);
    }

    // TODO: isNullOrEmpty vs isBlank
    // TODO: if we keep then we need a isNotNullOrEmpty
    @Override
    public S isNullOrEmpty() {

        return myself;
    }

    @Override
    public S isEmpty() {
        rule.addConstraint(new IsEmptyConstraint());
        return myself;
    }

    @Override
    public S isNotEmpty() {
        rule.addConstraint(new IsNotEmptyConstraint());
        return myself;
    }

    @Override
    public S isBlank() {
        return myself;
    }

    @Override
    public S isNotBlank() {
        return myself;
    }

    @Override
    public S containsWhitespaces() {
        return myself;
    }

    @Override
    public S containsOnlyWhitespaces() {
        return myself;
    }

    @Override
    public S doesNotContainAnyWhitespaces() {
        return myself;
    }

    @Override
    public S doesNotContainOnlyWhitespaces() {
        return myself;
    }

    // TODO: decide on appropriate method names for length
    public S length(int minLength, int maxLength) {
        rule.addConstraint(new LengthConstraint(minLength, maxLength));
        return myself;
    }

    @Override
    public S hasLength(int expected) {
        return myself;
    }

    @Override
    public S hasLengthLessThan(int expected) {
        return myself;
    }

    @Override
    public S hasLengthLessThanOrEqualTo(int expected) {
        return myself;
    }

    @Override
    public S hasLengthGreaterThan(int expected) {
        return myself;
    }

    @Override
    public S hasLengthGreaterThanOrEqualTo(int expected) {
        return myself;
    }

    @Override
    public S hasLengthBetween(int min, int max) {
        return myself;
    }

    @Override
    public S hasLineCount(int expected) {
        return myself;
    }

    @Override
    public S hasSameLengthAs(CharSequenceSubject other) {
        return myself;
    }

    @Override
    public S hasSameLengthAs(Object other) {
        return myself;
    }

    @Override
    public S hasSameLengthAs(Iterable<?> other) {
        return myself;
    }

    @Override
    public S isEqualToIgnoringCase(CharSequence expected) {
        return myself;
    }

    @Override
    public S isNotEqualToIgnoringCase(CharSequence expected) {
        return myself;
    }

    @Override
    public S containsOnlyDigits() {
        return myself;
    }

    @Override
    public S containsOnlyOnce(CharSequence sequence) {
        return myself;
    }

    @Override
    public S contains(CharSequence... values) {
        return myself;
    }

    @Override
    public S contains(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public S containsSequence(CharSequence... values) {
        return myself;
    }

    @Override
    public S containsSequence(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public S containsSubsequence(CharSequence... values) {
        return myself;
    }

    @Override
    public S containsSubsequence(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public S containsIgnoreCase(CharSequence sequence) {
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
        rule.addConstraint(new StartsWithConstraint(prefix));
        return myself;
    }

    @Override
    public S doesNotStartWith(CharSequence prefix) {
        return myself;
    }

    @Override
    public S endsWith(CharSequence suffix) {
        return myself;
    }

    @Override
    public S doesNotEndWith(CharSequence suffix) {
        return myself;
    }

    @Override
    public S matches(CharSequence regex) {
        return myself;
    }

    @Override
    public S doesNotMatch(CharSequence regex) {
        return myself;
    }

    @Override
    public S matches(Pattern pattern) {
        return myself;
    }

    @Override
    public S doesNotmatch(Pattern pattern) {
        return myself;
    }

    @Override
    public S isXmlEqualTo(CharSequence expectedXml) {
        return myself;
    }

    @Override
    public S inHexadecimal() {
        return myself;
    }

    @Override
    public S inUnicode() {
        return myself;
    }

    @Override
    public S isEqualToIgnoringWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public S isNotEqualToIgnoringWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public S isEqualToNormalizingWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public S isNotEqualToNormalizingWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public S isSubstringOf(CharSequence sequence) {
        return myself;
    }

    @Override
    public S containsPattern(CharSequence regex) {
        return myself;
    }

    @Override
    public S containsPattern(Pattern pattern) {
        return myself;
    }

    @Override
    public S isEqualToNormalizingNewlines(CharSequence expected) {
        return myself;
    }

    @Override
    public S isEqualToIgnoringNewLines(CharSequence expected) {
        return myself;
    }

    @Override
    public S isLowerCase() {
        return myself;
    }

    @Override
    public S isUpperCase() {
        return myself;
    }

}
