package jfluentvalidation.common;

import org.checkerframework.checker.nullness.qual.Nullable;

public class Strings {


    public static boolean isNull(@Nullable CharSequence charSequence) {
        return charSequence == null;
    }

    public static boolean isNullOrEmpty(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.toString().isEmpty();
    }
}
