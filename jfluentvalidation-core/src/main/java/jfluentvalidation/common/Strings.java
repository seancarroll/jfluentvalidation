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

    /**
     * JDK 8 String.replace is terribly slow. Use this as a replacement until at least JDK 9 at which point we can remove.
     *
     * @param source
     * @param os
     * @param ns
     * @return
     */
    public static String replace(String source, String os, String ns) {
        if (source == null) {
            return null;
        }
        int i = 0;
        if ((i = source.indexOf(os, i)) >= 0) {
            char[] sourceArray = source.toCharArray();
            char[] nsArray = ns.toCharArray();
            int oLength = os.length();
            StringBuilder buf = new StringBuilder(sourceArray.length);
            buf.append(sourceArray, 0, i).append(nsArray);
            i += oLength;
            int j = i;
            // Replace all remaining instances of oldString with newString.
            while ((i = source.indexOf(os, i)) > 0) {
                buf.append(sourceArray, j, i - j).append(nsArray);
                i += oLength;
                j = i;
            }
            buf.append(sourceArray, j, sourceArray.length - j);
            source = buf.toString();
            buf.setLength(0);
        }
        return source;
    }
}
