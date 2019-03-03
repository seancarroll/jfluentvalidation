package jfluentvalidation.common;

import org.checkerframework.checker.nullness.qual.Nullable;

public final class Strings {

    private Strings() {
        // statics only
    }

    public static boolean isNull(@Nullable CharSequence charSequence) {
        return charSequence == null;
    }

    public static boolean isNullOrEmpty(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.toString().isEmpty();
    }

    public static boolean containsWhitespaces(CharSequence value) {
        return value.chars().anyMatch(Character::isWhitespace);
    }

    public static boolean containsOnlyWhitespace(CharSequence value) {
        return value.chars().allMatch(Character::isWhitespace);
    }
}
