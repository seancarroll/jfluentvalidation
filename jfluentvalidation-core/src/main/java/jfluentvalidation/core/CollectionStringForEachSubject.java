package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.util.regex.Pattern;

// TODO: should we delete
// Using this pattern / style I would need one for each type I support which I think so but I guess whats the difference
// between that and the subject patter I'm already using?
// It's double the classes if not more to support Iterable, Array, and Map
public class CollectionStringForEachSubject
    extends CollectionForEachSubject<CollectionStringForEachSubject, Iterable<CharSequenceSubject>>
    implements ReadableSequence<CollectionStringForEachSubject, String>, ComparableSubject<StringSubject, String> {

    public CollectionStringForEachSubject(Class<?> selfType, PropertyRule<CollectionStringForEachSubject, Iterable<CharSequenceSubject>> rule) {
        super(selfType, rule);
    }

    @Override
    public CollectionStringForEachSubject isNullOrEmpty() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isEmpty() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isNotEmpty() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isBlank() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isNotBlank() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsWhitespaces() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsOnlyWhitespaces() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotContainAnyWhitespaces() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotContainOnlyWhitespaces() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasLength(int expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasLengthLessThan(int expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasLengthLessThanOrEqualTo(int expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasLengthGreaterThan(int expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasLengthGreaterThanOrEqualTo(int expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasLengthBetween(int min, int max) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasLineCount(int expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasSameLengthAs(CharSequenceSubject other) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasSameLengthAs(Object other) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject hasSameLengthAs(Iterable<?> other) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isEqualToIgnoringCase(CharSequence expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isNotEqualToIgnoringCase(CharSequence expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsOnlyDigits() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsOnlyOnce(CharSequence sequence) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject contains(CharSequence... values) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject contains(Iterable<? extends CharSequence> values) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsSequence(CharSequence... values) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsSequence(Iterable<? extends CharSequence> values) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsSubsequence(CharSequence... values) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsSubsequence(Iterable<? extends CharSequence> values) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsIgnoreCase(CharSequence sequence) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotContain(CharSequence... values) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotContain(Iterable<? extends CharSequence> values) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotContainPattern(CharSequence pattern) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotContainPattern(Pattern pattern) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject startsWith(CharSequence prefix) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotStartWith(CharSequence prefix) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject endsWith(CharSequence suffix) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotEndWith(CharSequence suffix) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject matches(CharSequence regex) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotMatch(CharSequence regex) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject matches(Pattern pattern) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject doesNotmatch(Pattern pattern) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isXmlEqualTo(CharSequence expectedXml) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject inHexadecimal() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject inUnicode() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isEqualToIgnoringWhitespace(CharSequence expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isNotEqualToIgnoringWhitespace(CharSequence expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isEqualToNormalizingWhitespace(CharSequence expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isNotEqualToNormalizingWhitespace(CharSequence expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isSubstringOf(CharSequence sequence) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsPattern(CharSequence regex) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject containsPattern(Pattern pattern) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isEqualToNormalizingNewlines(CharSequence expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isEqualToIgnoringNewLines(CharSequence expected) {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isLowerCase() {
        return null;
    }

    @Override
    public CollectionStringForEachSubject isUpperCase() {
        return null;
    }

    @Override
    public StringSubject isEqualAccordingToCompareTo(String other) {
        return null;
    }

    @Override
    public StringSubject isNotEqualAccordingToCompareTo(String other) {
        return null;
    }

    @Override
    public StringSubject isLessThan(String other) {
        return null;
    }

    @Override
    public StringSubject isLessThanOrEqualTo(String other) {
        return null;
    }

    @Override
    public StringSubject isGreaterThan(String other) {
        return null;
    }

    @Override
    public StringSubject isGreaterThanOrEqualTo(String other) {
        return null;
    }

    @Override
    public StringSubject isBetween(String startInclusive, String endInclusive) {
        return null;
    }

    @Override
    public StringSubject isStrictlyBetween(String startExclusive, String endExclusive) {
        return null;
    }

    @Override
    public StringSubject isBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public StringSubject isNotBetween(String startInclusive, String endInclusive) {
        return null;
    }

    @Override
    public StringSubject isNotBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
