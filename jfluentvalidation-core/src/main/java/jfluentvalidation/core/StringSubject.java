package jfluentvalidation.core;

import jfluentvalidation.constraints.charsequence.IsEmptyConstraint;
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
        return null;
    }

    @Override
    public StringSubject isEmpty() {
        constraints.add(new IsEmptyConstraint());
        return myself;
    }

    @Override
    public StringSubject isNotEmpty() {
        return null;
    }

    @Override
    public StringSubject isBlank() {
        return null;
    }

    @Override
    public StringSubject isNotBlank() {
        return null;
    }

    @Override
    public StringSubject containsWhitespaces() {
        return null;
    }

    @Override
    public StringSubject containsOnlyWhitespaces() {
        return null;
    }

    @Override
    public StringSubject doesNotContainAnyWhitespaces() {
        return null;
    }

    @Override
    public StringSubject doesNotContainOnlyWhitespaces() {
        return null;
    }

    // TODO: decide on appropriate method names for length
    public StringSubject length(int minLength, int maxLength) {
        constraints.add(new LengthConstraint(minLength, maxLength));
        return myself;
    }

    @Override
    public StringSubject hasLength(int expected) {
        return null;
    }

    @Override
    public StringSubject hasLengthLessThan(int expected) {
        return null;
    }

    @Override
    public StringSubject hasLengthLessThanOrEqualTo(int expected) {
        return null;
    }

    @Override
    public StringSubject hasLengthGreaterThan(int expected) {
        return null;
    }

    @Override
    public StringSubject hasLengthGreaterThanOrEqualTo(int expected) {
        return null;
    }

    @Override
    public StringSubject hasLengthBetween(int min, int max) {
        return null;
    }

    @Override
    public StringSubject hasLineCount(int expected) {
        return null;
    }

    @Override
    public StringSubject hasSameLengthAs(CharSequenceSubject other) {
        return null;
    }

    @Override
    public StringSubject hasSameLengthAs(Object other) {
        return null;
    }

    @Override
    public StringSubject hasSameLengthAs(Iterable<?> other) {
        return null;
    }

    @Override
    public StringSubject isEqualToIgnoringCase(CharSequence expected) {
        return null;
    }

    @Override
    public StringSubject isNotEqualToIgnoringCase(CharSequence expected) {
        return null;
    }

    @Override
    public StringSubject containsOnlyDigits() {
        return null;
    }

    @Override
    public StringSubject containsOnlyOnce(CharSequence sequence) {
        return null;
    }

    @Override
    public StringSubject contains(CharSequence... values) {
        return null;
    }

    @Override
    public StringSubject contains(Iterable<? extends CharSequence> values) {
        return null;
    }

    @Override
    public StringSubject containsSequence(CharSequence... values) {
        return null;
    }

    @Override
    public StringSubject containsSequence(Iterable<? extends CharSequence> values) {
        return null;
    }

    @Override
    public StringSubject containsSubsequence(CharSequence... values) {
        return null;
    }

    @Override
    public StringSubject containsSubsequence(Iterable<? extends CharSequence> values) {
        return null;
    }

    @Override
    public StringSubject containsIgnoreCase(CharSequence sequence) {
        return null;
    }

    @Override
    public StringSubject doesNotContain(CharSequence... values) {
        return null;
    }

    @Override
    public StringSubject doesNotContain(Iterable<? extends CharSequence> values) {
        return null;
    }

    @Override
    public StringSubject doesNotContainPattern(CharSequence pattern) {
        return null;
    }

    @Override
    public StringSubject doesNotContainPattern(Pattern pattern) {
        return null;
    }

    @Override
    public StringSubject startsWith(CharSequence prefix) {
        constraints.add(new StartsWithConstraint(prefix));
        return myself;
    }

    @Override
    public StringSubject doesNotStartWith(CharSequence prefix) {
        return null;
    }

    @Override
    public StringSubject endsWith(CharSequence suffix) {
        return null;
    }

    @Override
    public StringSubject doesNotEndWith(CharSequence suffix) {
        return null;
    }

    @Override
    public StringSubject matches(CharSequence regex) {
        return null;
    }

    @Override
    public StringSubject doesNotMatch(CharSequence regex) {
        return null;
    }

    @Override
    public StringSubject matches(Pattern pattern) {
        return null;
    }

    @Override
    public StringSubject doesNotmatch(Pattern pattern) {
        return null;
    }

    @Override
    public StringSubject isXmlEqualTo(CharSequence expectedXml) {
        return null;
    }

    @Override
    public StringSubject inHexadecimal() {
        return null;
    }

    @Override
    public StringSubject inUnicode() {
        return null;
    }

    @Override
    public StringSubject isEqualToIgnoringWhitespace(CharSequence expected) {
        return null;
    }

    @Override
    public StringSubject isNotEqualToIgnoringWhitespace(CharSequence expected) {
        return null;
    }

    @Override
    public StringSubject isEqualToNormalizingWhitespace(CharSequence expected) {
        return null;
    }

    @Override
    public StringSubject isNotEqualToNormalizingWhitespace(CharSequence expected) {
        return null;
    }

    @Override
    public StringSubject isSubstringOf(CharSequence sequence) {
        return null;
    }

    @Override
    public StringSubject containsPattern(CharSequence regex) {
        return null;
    }

    @Override
    public StringSubject containsPattern(Pattern pattern) {
        return null;
    }

    @Override
    public StringSubject isEqualToNormalizingNewlines(CharSequence expected) {
        return null;
    }

    @Override
    public StringSubject isEqualToIgnoringNewLines(CharSequence expected) {
        return null;
    }

    @Override
    public StringSubject isLowerCase() {
        return null;
    }

    @Override
    public StringSubject isUpperCase() {
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

//    public StringSubject isEmpty() {
//        constraints.add(new IsEmptyConstraint());
//        return myself;
//    }
//
//    public StringSubject isNotEmpty() {
//        constraints.add(new IsNotEmptyConstraint());
//        return myself;
//    }
//
//    public StringSubject minLength(int minLength) {
//        constraints.add(new LengthConstraint(minLength, -1));
//        return myself;
//    }
//
//    public StringSubject maxLength(int maxLength) {
//        constraints.add((new LengthConstraint(0, maxLength)));
//        return myself;
//    }
//
//    public StringSubject exactLength(int length) {
//        constraints.add(new LengthConstraint(length, length));
//        return myself;
//    }
//
//    public StringSubject length(int minLength, int maxLength) {
//        constraints.add(new LengthConstraint(minLength, maxLength));
//        return myself;
//    }
//
//    public StringSubject startsWith(String prefix) {
//        return startsWith(prefix, 0);
//    }
//
//    public StringSubject startsWith(String prefix, int offset) {
//        constraints.add(new StartsWithConstraint(prefix));
//        return myself;
//    }



}
