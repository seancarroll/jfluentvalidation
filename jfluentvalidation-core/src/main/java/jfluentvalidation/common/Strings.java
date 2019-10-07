package jfluentvalidation.common;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 */
public final class Strings {

    private Strings() {
        // statics only
    }

    /**
     *
     * @param charSequence
     * @return
     */
    public static boolean isNull(@Nullable CharSequence charSequence) {
        return charSequence == null;
    }

    /**
     *
     * @param charSequence
     * @return
     */
    public static boolean isNullOrEmpty(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.toString().isEmpty();
    }

    // TODO: add blank?

    /**
     *
     * @param value
     * @return
     */
    public static boolean containsWhitespaces(CharSequence value) {
        return value.chars().anyMatch(Character::isWhitespace);
    }

    /**
     *
     * @param value
     * @return
     */
    public static boolean containsOnlyWhitespace(CharSequence value) {
        return value.chars().allMatch(Character::isWhitespace);
    }

    public static boolean isLowerCase(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isLowerCase(charSequence.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isUpperCase(CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isUpperCase(charSequence.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
