package jfluentvalidation.internal;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 *
 */
public final class Ensure {

    private Ensure() {
        // statics only
    }

    /**
     *
     * @param reference
     * @param <T>
     * @return
     */
    @CanIgnoreReturnValue
    public static <T> T notNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     *
     * @param reference
     * @param errorMessage
     * @param <T>
     * @return
     */
    @CanIgnoreReturnValue
    public static <T> T notNull(T reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }

    /**
     *
     * @param number
     * @param argumentName
     * @return
     */
    @CanIgnoreReturnValue
    public static int positive(int number, String argumentName) {
        if (number <= 0) {
            throw new IllegalArgumentException(argumentName + " should be positive.");
        }
        return number;
    }

    /**
     *
     * @param number
     * @param argumentName
     * @return
     */
    @CanIgnoreReturnValue
    public static long positive(long number, String argumentName) {
        if (number <= 0) {
            throw new IllegalArgumentException(argumentName + " should be positive.");
        }
        return number;
    }

    /**
     *
     * @param number
     * @param argumentName
     * @return
     */
    @CanIgnoreReturnValue
    public static int nonnegative(int number, String argumentName) {
        if (number < 0) {
            throw new IllegalArgumentException(argumentName + " should be non negative.");
        }
        return number;
    }

    /**
     *
     * @param number
     * @param argumentName
     * @return
     */
    @CanIgnoreReturnValue
    public static long nonnegative(long number, String argumentName) {
        if (number < 0) {
            throw new IllegalArgumentException(argumentName + " should be non negative.");
        }
        return number;
    }

    /**
     *
     * @param expression
     */
    public static void argument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param expression
     * @param errorMessage
     */
    public static void argument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }


    public static <T> T[] notNullOrEmpty(T[] arr) {
        notNull(arr);
        notEmpty(arr);
        return arr;
    }

    public static <T> void notEmpty(T[] arr) {
        argument(arr.length > 0, "Array should not be empty");
    }

    /**
     *
     * @param sequences
     * @return
     */
    // TODO: do we need this? dont know where this should actually go
    public static CharSequence[] validCharSequenceArray(CharSequence[] sequences) {
        notNull(sequences);
        argument(sequences.length > 0, "Sequences should not be empty");
        // TODO: turn this into arrayDoesNotContainNulls?
        // TODO: getting warning that i < sequences.length is always false
        for (int i = 0; i < sequences.length; i++) {
            notNull(sequences[i], "CharSequence elements should not be null but found one at index " + i);
        }
        return sequences;
    }
}
