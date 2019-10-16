package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.regex.Pattern;

// TODO: better name?

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}.
 */
public interface ReadableSequence<S extends ReadableSequence<S, T, A>, T, A> {

    @CanIgnoreReturnValue
    void isNullOrEmpty();

    @CanIgnoreReturnValue
    void isEmpty();

    @CanIgnoreReturnValue
    S isNotEmpty();

    @CanIgnoreReturnValue
    // TODO: do we want this? Is it common language?
    S isBlank();

    @CanIgnoreReturnValue
    S isNotBlank();

    @CanIgnoreReturnValue
    S containsWhitespaces();

    @CanIgnoreReturnValue
    S containsOnlyWhitespaces();

    @CanIgnoreReturnValue
    S doesNotContainAnyWhitespaces();

    @CanIgnoreReturnValue
    S doesNotContainOnlyWhitespaces();

    @CanIgnoreReturnValue
    S hasLength(int expected);

    @CanIgnoreReturnValue
    S hasLengthLessThan(int expected);

    @CanIgnoreReturnValue
    S hasLengthLessThanOrEqualTo(int expected);

    @CanIgnoreReturnValue
    S hasLengthGreaterThan(int expected);

    @CanIgnoreReturnValue
    S hasLengthGreaterThanOrEqualTo(int expected);

    @CanIgnoreReturnValue
    S hasLengthBetween(int min, int max);

    @CanIgnoreReturnValue
    S hasLengthBetween(int min, int max, boolean inclusiveStart, boolean inclusiveEnd);

    @CanIgnoreReturnValue
    S hasSameLengthAs(CharSequence other);

    @CanIgnoreReturnValue
    S isEqualToIgnoringCase(CharSequence expected);

    @CanIgnoreReturnValue
    // TODO: expected here as parameter name might not make sense...other might be more appropriate
    S isNotEqualToIgnoringCase(CharSequence expected);

    @CanIgnoreReturnValue
    S containsOnlyDigits();

    @CanIgnoreReturnValue
    // TODO: Do we want to keep? Better name?
    S containsOnlyOnce(CharSequence sequence);

    @CanIgnoreReturnValue
    S contains(CharSequence... values);

    @CanIgnoreReturnValue
    S contains(Iterable<? extends  CharSequence> values);

    @CanIgnoreReturnValue
    S containsIgnoreCase(CharSequence sequence);

    @CanIgnoreReturnValue
    S doesNotContain(CharSequence... values);

    @CanIgnoreReturnValue
    S doesNotContain(Iterable<? extends CharSequence> values);

    @CanIgnoreReturnValue
    S doesNotContainPattern(CharSequence pattern);

    @CanIgnoreReturnValue
    S doesNotContainPattern(Pattern pattern);

    @CanIgnoreReturnValue
    S startsWith(CharSequence prefix);

    @CanIgnoreReturnValue
    S startsWith(CharSequence prefix, int offset);

    @CanIgnoreReturnValue
    S doesNotStartWith(CharSequence prefix);

    @CanIgnoreReturnValue
    S endsWith(CharSequence suffix);

    @CanIgnoreReturnValue
    S doesNotEndWith(CharSequence suffix);

    @CanIgnoreReturnValue
    S matches(CharSequence regex);

    @CanIgnoreReturnValue
    S doesNotMatch(CharSequence regex);

    @CanIgnoreReturnValue
    S matches(Pattern pattern);

    @CanIgnoreReturnValue
    S doesNotMatch(Pattern pattern);

    @CanIgnoreReturnValue
    S isEqualToIgnoringWhitespace(CharSequence expected);

    @CanIgnoreReturnValue
    S isNotEqualToIgnoringWhitespace(CharSequence expected);

    @CanIgnoreReturnValue
    S isEqualToNormalizingWhitespace(CharSequence expected);

    @CanIgnoreReturnValue
    S isNotEqualToNormalizingWhitespace(CharSequence expected);

    @CanIgnoreReturnValue
    S isSubstringOf(CharSequence sequence);

    @CanIgnoreReturnValue
    S containsPattern(CharSequence regex);

    @CanIgnoreReturnValue
    S containsPattern(Pattern pattern);

    @CanIgnoreReturnValue
    S isLowerCase();

    @CanIgnoreReturnValue
    S isUpperCase();

    @CanIgnoreReturnValue
    S isEmail();

}
