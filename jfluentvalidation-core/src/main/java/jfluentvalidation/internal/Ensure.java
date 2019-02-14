package jfluentvalidation.internal;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

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

    public static void positive(int number, String argumentName) {
        if (number <= 0) {
            throw new IllegalArgumentException(argumentName + " should be positive.");
        }
    }

    public static void positive(long number, String argumentName) {
        if (number <= 0) {
            throw new IllegalArgumentException(argumentName + " should be positive.");
        }
    }

    public static void nonnegative(int number, String argumentName) {
        if (number < 0) {
            throw new IllegalArgumentException(argumentName + " should be non negative.");
        }
    }

    public static void nonnegative(long number, String argumentName) {
        if (number < 0) {
            throw new IllegalArgumentException(argumentName + " should be non negative.");
        }
    }

    public static void argument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void argument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
