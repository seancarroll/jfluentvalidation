package jfluentvalidation.common;

import org.checkerframework.checker.nullness.qual.Nullable;

import javax.annotation.Nonnull;

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
    public static boolean containsWhitespaces(@Nonnull CharSequence value) {
        for (int i = 0; i < value.length(); i++) {
            if (Character.isWhitespace(value.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param value
     * @return
     */
    public static boolean containsOnlyWhitespace(@Nonnull CharSequence value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the CharSequence contains only lowercase characters.
     *
     * @param charSequence  the CharSequence to check, may be null
     * @return true if only contains lowercase characters, and is non-null
     */
    public static boolean isLowerCase(@Nonnull CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isLowerCase(charSequence.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the CharSequence contains only uppercase characters.
     *
     * @param charSequence  the CharSequence to check, may be null
     * @return true if only contains uppercase characters, and is non-null
     */
    public static boolean isUpperCase(@Nonnull CharSequence charSequence) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isUpperCase(charSequence.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
