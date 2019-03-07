package jfluentvalidation.core;

import jfluentvalidation.constraints.charsequence.IsEmptyConstraint;
import jfluentvalidation.constraints.charsequence.IsNotEmptyConstraint;
import jfluentvalidation.constraints.charsequence.LengthConstraint;
import jfluentvalidation.constraints.charsequence.StartsWithConstraint;

import java.util.function.Function;
import java.util.regex.Pattern;

public class StringSubject extends Subject<StringSubject, String> implements CharSequenceSubject<StringSubject, String>, ComparableSubject<StringSubject, String> {

    public StringSubject(Function func, String propertyName) {
        super(StringSubject.class, func, propertyName);
    }

    // TODO: isNullOrEmpty vs isBlank
    // TODO: if we keep then we need a isNotNullOrEmpty
    @Override
    public StringSubject isNullOrEmpty() {

        return myself;
    }

    @Override
    public StringSubject isEmpty() {
        constraints.add(new IsEmptyConstraint());
        return myself;
    }

    @Override
    public StringSubject isNotEmpty() {
        constraints.add(new IsNotEmptyConstraint());
        return myself;
    }

    @Override
    public StringSubject isBlank() {
        return myself;
    }

    @Override
    public StringSubject isNotBlank() {
        return myself;
    }

    @Override
    public StringSubject containsWhitespaces() {
        return myself;
    }

    @Override
    public StringSubject containsOnlyWhitespaces() {
        return myself;
    }

    @Override
    public StringSubject doesNotContainAnyWhitespaces() {
        return myself;
    }

    @Override
    public StringSubject doesNotContainOnlyWhitespaces() {
        return myself;
    }

    // TODO: decide on appropriate method names for length
    public StringSubject length(int minLength, int maxLength) {
        constraints.add(new LengthConstraint(minLength, maxLength));
        return myself;
    }

    @Override
    public StringSubject hasLength(int expected) {
        return myself;
    }

    @Override
    public StringSubject hasLengthLessThan(int expected) {
        return myself;
    }

    @Override
    public StringSubject hasLengthLessThanOrEqualTo(int expected) {
        return myself;
    }

    @Override
    public StringSubject hasLengthGreaterThan(int expected) {
        return myself;
    }

    @Override
    public StringSubject hasLengthGreaterThanOrEqualTo(int expected) {
        return myself;
    }

    @Override
    public StringSubject hasLengthBetween(int min, int max) {
        return myself;
    }

    @Override
    public StringSubject hasLineCount(int expected) {
        return myself;
    }

    @Override
    public StringSubject hasSameLengthAs(CharSequenceSubject other) {
        return myself;
    }

    @Override
    public StringSubject hasSameLengthAs(Object other) {
        return myself;
    }

    @Override
    public StringSubject hasSameLengthAs(Iterable<?> other) {
        return myself;
    }

    @Override
    public StringSubject isEqualToIgnoringCase(CharSequence expected) {
        return myself;
    }

    @Override
    public StringSubject isNotEqualToIgnoringCase(CharSequence expected) {
        return myself;
    }

    @Override
    public StringSubject containsOnlyDigits() {
        return myself;
    }

    @Override
    public StringSubject containsOnlyOnce(CharSequence sequence) {
        return myself;
    }

    @Override
    public StringSubject contains(CharSequence... values) {
        return myself;
    }

    @Override
    public StringSubject contains(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public StringSubject containsSequence(CharSequence... values) {
        return myself;
    }

    @Override
    public StringSubject containsSequence(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public StringSubject containsSubsequence(CharSequence... values) {
        return myself;
    }

    @Override
    public StringSubject containsSubsequence(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public StringSubject containsIgnoreCase(CharSequence sequence) {
        return myself;
    }

    @Override
    public StringSubject doesNotContain(CharSequence... values) {
        return myself;
    }

    @Override
    public StringSubject doesNotContain(Iterable<? extends CharSequence> values) {
        return myself;
    }

    @Override
    public StringSubject doesNotContainPattern(CharSequence pattern) {
        return myself;
    }

    @Override
    public StringSubject doesNotContainPattern(Pattern pattern) {
        return myself;
    }

    @Override
    public StringSubject startsWith(CharSequence prefix) {
        constraints.add(new StartsWithConstraint(prefix));
        return myself;
    }

    @Override
    public StringSubject doesNotStartWith(CharSequence prefix) {
        return myself;
    }

    @Override
    public StringSubject endsWith(CharSequence suffix) {
        return myself;
    }

    @Override
    public StringSubject doesNotEndWith(CharSequence suffix) {
        return myself;
    }

    @Override
    public StringSubject matches(CharSequence regex) {
        return myself;
    }

    @Override
    public StringSubject doesNotMatch(CharSequence regex) {
        return myself;
    }

    @Override
    public StringSubject matches(Pattern pattern) {
        return myself;
    }

    @Override
    public StringSubject doesNotmatch(Pattern pattern) {
        return myself;
    }

    @Override
    public StringSubject isXmlEqualTo(CharSequence expectedXml) {
        return myself;
    }

    @Override
    public StringSubject inHexadecimal() {
        return myself;
    }

    @Override
    public StringSubject inUnicode() {
        return myself;
    }

    @Override
    public StringSubject isEqualToIgnoringWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public StringSubject isNotEqualToIgnoringWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public StringSubject isEqualToNormalizingWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public StringSubject isNotEqualToNormalizingWhitespace(CharSequence expected) {
        return myself;
    }

    @Override
    public StringSubject isSubstringOf(CharSequence sequence) {
        return myself;
    }

    @Override
    public StringSubject containsPattern(CharSequence regex) {
        return myself;
    }

    @Override
    public StringSubject containsPattern(Pattern pattern) {
        return myself;
    }

    @Override
    public StringSubject isEqualToNormalizingNewlines(CharSequence expected) {
        return myself;
    }

    @Override
    public StringSubject isEqualToIgnoringNewLines(CharSequence expected) {
        return myself;
    }

    @Override
    public StringSubject isLowerCase() {
        return myself;
    }

    @Override
    public StringSubject isUpperCase() {
        return myself;
    }

    @Override
    public StringSubject isEqualAccordingToCompareTo(String other) {
        return myself;
    }

    @Override
    public StringSubject isNotEqualAccordingToCompareTo(String other) {
        return myself;
    }

    @Override
    public StringSubject isLessThan(String other) {
        return myself;
    }

    @Override
    public StringSubject isLessThanOrEqualTo(String other) {
        return myself;
    }

    @Override
    public StringSubject isGreaterThan(String other) {
        return myself;
    }

    @Override
    public StringSubject isGreaterThanOrEqualTo(String other) {
        return myself;
    }

    @Override
    public StringSubject isBetween(String startInclusive, String endInclusive) {
        return myself;
    }

    @Override
    public StringSubject isStrictlyBetween(String startExclusive, String endExclusive) {
        return myself;
    }

    @Override
    public StringSubject isBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public StringSubject isNotBetween(String startInclusive, String endInclusive) {
        return myself;
    }

    @Override
    public StringSubject isNotBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

}
