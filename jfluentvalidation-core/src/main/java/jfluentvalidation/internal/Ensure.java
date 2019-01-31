package jfluentvalidation.internal;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

// TODO: use guava or not
public final class Ensure {

    private Ensure() {
        // statics only
    }

    @CanIgnoreReturnValue
    public static <T> T notNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    @CanIgnoreReturnValue
    public static <T> T notNull(T reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }

}
