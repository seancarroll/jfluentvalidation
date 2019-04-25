package jfluentvalidation.constraints.map;

import java.util.Map;

/**
 *
 */
public final class MapConstraints {

    private static final IsEmptyConstraint IS_EMPTY_CONSTRAINT = new IsEmptyConstraint();
    private static final IsNotEmptyConstraint IS_NOT_EMPTY_CONSTRAINT = new IsNotEmptyConstraint();

    public static <K,V>  ContainsEntryConstraint containsEntry(Map.Entry<K, V> entry) {
        return new ContainsEntryConstraint<>(entry);
    }

    public static <K> ContainsKeyConstraint containsKey(K key) {
        return new ContainsKeyConstraint<>(key);
    }

    public static <V> ContainsValueConstraint containsValue(V value) {
        return new ContainsValueConstraint<>(value);
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
