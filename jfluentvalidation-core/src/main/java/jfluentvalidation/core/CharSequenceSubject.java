package jfluentvalidation.core;

import java.util.regex.Pattern;

public interface CharSequenceSubject<S extends CharSequenceSubject<S, A>, A extends CharSequence> {

//    public CharSequenceSubject(Class<?> selfType, Function<Object, T> func, String propertyName) {
//        super(selfType, func, propertyName);
//    }

    S isNullOrEmpty();

    S isEmpty();

    S isNotEmpty();

    // TODO: do we want this? Is it common language?
    S isBlank();

    S isNotBlank();

    // TODO: do we want this? Not sure this is helpful
    S containsWhitespaces();

    // TODO: do we want this? Not sure this is helpful
    S containsOnlyWhitespaces();

    S doesNotContainAnyWhitespaces();

    S doesNotContainOnlyWhitespaces();

    S hasLength(int expected);

    S hasLengthLessThan(int expected);

    S hasLengthLessThanOrEqualTo(int expected);

    S hasLengthGreaterThan(int expected);

    S hasLengthGreaterThanOrEqualTo(int expected);

    S hasLengthBetween(int min, int max);

    // TODO: do we want to keep this? Not sure how helpful
    S hasLineCount(int expected);

    S hasSameLengthAs(CharSequenceSubject other);

    // TODO: This is for an array. Why not just pass in an array? Do we want to keep? Does this make sense?
    S hasSameLengthAs(Object other);

    S hasSameLengthAs(Iterable<?> other);

    S isEqualToIgnoringCase(CharSequence expected);

    // TODO: expected here as parameter name might not make sense...other might be more appropriate
    S isNotEqualToIgnoringCase(CharSequence expected);

    S containsOnlyDigits();

    // TODO: Do we want to keep? Better name?
    S containsOnlyOnce(CharSequence sequence);

    S contains(CharSequence... values);

    S contains(Iterable<? extends  CharSequence> values);

    S containsSequence(CharSequence... values);

    S containsSequence(Iterable<? extends CharSequence> values);

    S containsSubsequence(CharSequence... values);

    S containsSubsequence(Iterable<? extends CharSequence> values);

    S containsIgnoreCase(CharSequence sequence);

    S doesNotContain(CharSequence... values);

    S doesNotContain(Iterable<? extends CharSequence> values);

    S doesNotContainPattern(CharSequence pattern);

    S doesNotContainPattern(Pattern pattern);

    S startsWith(CharSequence prefix);

    S doesNotStartWith(CharSequence prefix);

    S endsWith(CharSequence suffix);

    S doesNotEndWith(CharSequence suffix);

    S matches(CharSequence regex);

    S doesNotMatch(CharSequence regex);

    S matches(Pattern pattern);

    S doesNotmatch(Pattern pattern);

    // TODO: Not sure this makes sense to keep
    S isXmlEqualTo(CharSequence expectedXml);

    S inHexadecimal();

    S inUnicode();

    S isEqualToIgnoringWhitespace(CharSequence expected);

    S isNotEqualToIgnoringWhitespace(CharSequence expected);

    S isEqualToNormalizingWhitespace(CharSequence expected);

    S isNotEqualToNormalizingWhitespace(CharSequence expected);

    S isSubstringOf(CharSequence sequence);

    S containsPattern(CharSequence regex);

    S containsPattern(Pattern pattern);

    S isEqualToNormalizingNewlines(CharSequence expected);

    S isEqualToIgnoringNewLines(CharSequence expected);

    S isLowerCase();

    S isUpperCase();

    // TODO: isEmail();
}
