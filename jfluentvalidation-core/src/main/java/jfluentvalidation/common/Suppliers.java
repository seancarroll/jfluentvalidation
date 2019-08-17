package jfluentvalidation.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.function.Supplier;

public final class Suppliers {

    // Guava have Suppliers with ofInstance
    // TODO: might be a shitty idea and I should just add the darn null check before creating the supplier
    /**
     * Creates a supplier from the reference object with a null check before creating the supplier
     *
     * @param reference
     * @param <T>
     * @return
     */
    @CanIgnoreReturnValue
    public static <T> Supplier<T> create(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return () -> reference;
    }

    private Suppliers() {
        // statics only
    }
}
