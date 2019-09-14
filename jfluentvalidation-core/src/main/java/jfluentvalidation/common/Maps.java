package jfluentvalidation.common;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 */
public class Maps {

    private Maps() {
        // statics only
    }

    /**
     *
     * @param key
     * @param value
     * @param <K>  the type of keys maintained by this map
     * @param <V>  the type of mapped values
     * @return
     */
    public static <K, V>  Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    // TODO: I dont love the term "actual" here but I do use it in other common classes...
    /**
     *
     * @param actual
     * @param entry
     * @param <K>  the type of keys maintained by this map
     * @param <V>  the type of mapped values
     * @return  Returns true if this map contains a mapping for the specified key.
     * More formally, returns true if and only if this map contains a mapping for a key k such that (key==null ? k==null : key.equals(k)).
     * (There can be at most one such mapping.)
     */
    public static <K, V> boolean containsEntry(Map<K, V> actual, Map.Entry<? extends K, ? extends V> entry) {
        return entry != null
            && actual.containsKey(entry.getKey())
            && Objects.equals(actual.get(entry.getKey()), entry.getValue());
    }

}
