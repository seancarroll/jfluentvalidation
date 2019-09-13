package jfluentvalidation.common;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

public class Maps {

    private Maps() {
        // statics only
    }

    public static <K, V>  Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    public static <K, V> boolean containsEntry(Map<K, V> actual, Map.Entry<? extends K, ? extends V> entry) {
        return entry != null
            && actual.containsKey(entry.getKey())
            && Objects.equals(actual.get(entry.getKey()), entry.getValue());
    }
}
