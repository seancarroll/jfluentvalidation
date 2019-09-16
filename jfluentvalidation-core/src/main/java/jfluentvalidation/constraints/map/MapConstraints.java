package jfluentvalidation.constraints.map;

import java.util.Map;

// TODO: singleton instead of statics?
/**
 *
 */
public final class MapConstraints {

    private static final IsEmptyConstraint IS_EMPTY_CONSTRAINT = new IsEmptyConstraint();
    private static final IsNotEmptyConstraint IS_NOT_EMPTY_CONSTRAINT = new IsNotEmptyConstraint();

    public static <K, V> ContainsEntriesConstraint containsEntries(Map.Entry<K, V>... entries) {
        return new ContainsEntriesConstraint<>(entries);
    }

    @SafeVarargs
    public static <K> ContainsKeysConstraint containsKeys(K... keys) {
        return new ContainsKeysConstraint<>(keys);
    }

    @SafeVarargs
    public static <V> ContainsValuesConstraint containsValues(V... values) {
        return new ContainsValuesConstraint<>(values);
    }

    public static HasSizeConstraint hasSize(int size) {
        return new HasSizeConstraint(size);
    }

    public static IsEmptyConstraint isEmpty() {
        return IS_EMPTY_CONSTRAINT;
    }

    public static IsNotEmptyConstraint isNotEmpty() {
        return IS_NOT_EMPTY_CONSTRAINT;
    }

    private MapConstraints() {
        // public static factory methods only
    }
}
