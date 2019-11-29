package jfluentvalidation.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.common.annotations.VisibleForTesting;
import jfluentvalidation.internal.Ensure;

import java.util.function.Supplier;

/**
 *
 */
public final class Suppliers {

    private Suppliers() {
        // statics only
    }

    /**
     * Creates a supplier from the reference object with a null check before creating the supplier
     *
     * @param reference
     * @param <T>
     * @return
     */
    @CanIgnoreReturnValue
    public static <T> Supplier<T> create(T reference) {
        return create(reference, false);
    }

    /**
     * Creates a supplier from the reference object with a null check before creating the supplier
     *
     * @param reference
     * @param memoize
     * @param <T>
     * @return
     */
    public static <T> Supplier<T> create(T reference, boolean memoize) {
        Ensure.notNull(reference);

        Supplier<T> supplier = () -> reference;
        return memoize
            ? new MemoizingSupplier<>(supplier)
            : supplier;
    }

    public static <T> Supplier<T> memoize(Supplier<T> delegate) {
        if (delegate instanceof MemoizingSupplier) {
            return delegate;
        }
        return new MemoizingSupplier<>(delegate);
    }


    @VisibleForTesting
    static class MemoizingSupplier<T> implements Supplier<T> {
        final Supplier<T> delegate;
        volatile boolean initialized;
        T value;

        MemoizingSupplier(Supplier<T> delegate) {
            this.delegate = Ensure.notNull(delegate);
        }

        @Override
        public T get() {
            if (!initialized) {
                synchronized (this) {
                    if (!initialized) {
                        T t = delegate.get();
                        value = t;
                        initialized = true;
                        return t;
                    }
                }
            }
            return value;
        }

        @Override
        public String toString() {
            return "Suppliers.memoize("
                + (initialized ? "<supplier that returned " + value + ">" : delegate)
                + ")";
        }
    }

}
