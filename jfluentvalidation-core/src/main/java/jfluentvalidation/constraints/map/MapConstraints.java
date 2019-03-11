package jfluentvalidation.constraints.map;

import java.util.Map;

public final class MapConstraints {

    public static <K,V>  ContainsEntryConstraint containsEntry(Map.Entry<K, V> entry) {
        return new ContainsEntryConstraint();
    }

    public static <K> ContainsKeyConstraint containsKey(K key) {
        return new ContainsKeyConstraint();
    }

    public static <V> ContainsValueConstraint containsValue(V value) {
        return new ContainsValueConstraint(value);
    }

    public static HasSizeConstraint hasSize(int size) {
        return new HasSizeConstraint(size);
    }

    public static IsEmptyConstraint isEmpty() {
        // TODO: use a final static version of this
        return new IsEmptyConstraint();
    }

    public static IsNotEmptyConstraint isNotEmpty() {
        // TODO: use a final static version of this
        return new IsNotEmptyConstraint();
    }

    private MapConstraints() {
        // public static factory methods only
    }
}
