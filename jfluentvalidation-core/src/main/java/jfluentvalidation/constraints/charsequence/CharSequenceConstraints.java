package jfluentvalidation.constraints.charsequence;

import java.util.regex.Pattern;

/**
 *
 */
public final class CharSequenceConstraints {

    private static final ContainsOnlyDigitsConstraint CONTAINS_ONLY_DIGITS_CONSTRAINT = new ContainsOnlyDigitsConstraint();
    private static final ContainsOnlyWhitespacesConstraint CONTAINS_ONLY_WHITESPACES_CONSTRAINT = new ContainsOnlyWhitespacesConstraint();
    private static final ContainsWhitespacesConstraint CONTAINS_WHITESPACES_CONSTRAINT = new ContainsWhitespacesConstraint();
    private static final DoesNotContainAnyWhitespacesConstraint DOES_NOT_CONTAIN_ANY_WHITESPACES_CONSTRAINT = new DoesNotContainAnyWhitespacesConstraint();
    private static final IsEmptyConstraint IS_EMPTY_CONSTRAINT = new IsEmptyConstraint();
    private static final IsLowerCaseConstraint IS_LOWER_CASE_CONSTRAINT = new IsLowerCaseConstraint();
    private static final IsNotEmptyConstraint IS_NOT_EMPTY_CONSTRAINT = new IsNotEmptyConstraint();
    private static final IsUpperCaseConstraint IS_UPPER_CASE_CONSTRAINT = new IsUpperCaseConstraint();

    public static ContainsConstraint contains(CharSequence... sequences) {
        return new ContainsConstraint(sequences);
    }

    public static ContainsIgnoreCaseConstraint containsIgnoreCase(CharSequence sequence) {
        return new ContainsIgnoreCaseConstraint(sequence);
    }

    public static ContainsOnlyDigitsConstraint containsOnlyDigits() {
        return CONTAINS_ONLY_DIGITS_CONSTRAINT;
    }

    public static ContainsOnlyOnceConstraint containsOnlyOnce(CharSequence sequence) {
        return new ContainsOnlyOnceConstraint(sequence);
    }

    public static ContainsOnlyWhitespacesConstraint containsOnlyWhitespaces() {
        return CONTAINS_ONLY_WHITESPACES_CONSTRAINT;
    }

    public static ContainsPatternConstraint containsPattern(Pattern pattern) {
        return new ContainsPatternConstraint(pattern);
    }

    public static ContainsPatternConstraint containsPattern(CharSequence regex) {
        return new ContainsPatternConstraint(regex);
    }

    public static ContainsSequenceConstraint containsSequence(CharSequence... sequences) {
        return new ContainsSequenceConstraint(sequences);
    }

    public static ContainsSubsequenceConstraint containsSubsequence(CharSequence... sequences) {
        return new ContainsSubsequenceConstraint(sequences);
    }

    public static ContainsWhitespacesConstraint containsWhitespaces() {
        return CONTAINS_WHITESPACES_CONSTRAINT;
    }

    public static DoesNotContainAnyWhitespacesConstraint doesNotContainAnyWhitespaces() {
        return DOES_NOT_CONTAIN_ANY_WHITESPACES_CONSTRAINT;
    }

    public static EndsWithConstraint endsWith(CharSequence suffix) {
        return new EndsWithConstraint(suffix);
    }

    public static HasLengthBetweenConstraint hasLengthBetween(int min, int max) {
        return new HasLengthBetweenConstraint(min, max);
    }

    public static HasLengthConstraint hasLength(int length) {
        return new HasLengthConstraint(length);
    }

    public static HasLengthGreaterThanConstraint hasLengthGreaterThan(int length) {
        return new HasLengthGreaterThanConstraint(length);
    }

    public static HasLengthGreaterThanOrEqualToConstraint hasLengthGreaterThanOrEqualTo(int length) {
        return new HasLengthGreaterThanOrEqualToConstraint(length);
    }

    public static HasLengthLessThanConstraint hasLengthLessThan(int length) {
        return new HasLengthLessThanConstraint(length);
    }

    public static HasLengthLessThanOrEqualToConstraint hasLengthLessThanOrEqualToConstraint(int length) {
        return new HasLengthLessThanOrEqualToConstraint(length);
    }

    public static HasSameLengthAsConstraint hasSameLenghtAs(CharSequence other) {
        return new HasSameLengthAsConstraint(other);
    }

    // TODO: isBlank

    // TODO: always return a single instance (make a singleton public static final)
    public static IsEmailConstraint isEmail(CharSequence sequence) {
        return new IsEmailConstraint();
    }

    public static IsEmptyConstraint isEmpty() {
        return IS_EMPTY_CONSTRAINT;
    }

    public static IsEqualToIgnoringCaseConstraint isEqualToIgnoringCase(CharSequence other) {
        return new IsEqualToIgnoringCaseConstraint(other);
    }

    public static IsEqualToIgnoringWhitespaceConstraint isEqualToIgnoringWhitespace(CharSequence expected) {
        return new IsEqualToIgnoringWhitespaceConstraint(expected);
    }

    public static IsEqualToNormalizingWhitespaceConstraint isEqualToNormalizingWhitespace(CharSequence expected) {
        return new IsEqualToNormalizingWhitespaceConstraint(expected);
    }

    public static IsLowerCaseConstraint isLowerCase() {
        return IS_LOWER_CASE_CONSTRAINT;
    }

    public static IsNotEmptyConstraint isNotEmpty() {
        return IS_NOT_EMPTY_CONSTRAINT;
    }

    public static IsSubstringOfConstraint isSubstringOf(CharSequence sequence) {
        return new IsSubstringOfConstraint(sequence);
    }

    public static IsUpperCaseConstraint isUpperCase() {
        return IS_UPPER_CASE_CONSTRAINT;
    }

    // TODO: this might go away as I think we have too many length constraints
    public static LengthConstraint length(int min, int max) {
        return new LengthConstraint(min, max);
    }

    public static MatchesConstraint matches(CharSequence sequence) {
        return new MatchesConstraint(sequence);
    }

    public static MatchesConstraint matches(Pattern pattern) {
        return new MatchesConstraint(pattern);
    }

    public static StartsWithConstraint startsWith(CharSequence prefix) {
        return new StartsWithConstraint(prefix);
    }

    public static StartsWithConstraint startsWith(CharSequence prefix, int offset) {
        return new StartsWithConstraint(prefix, offset);
    }

    private CharSequenceConstraints() {
        // statics only
    }
}