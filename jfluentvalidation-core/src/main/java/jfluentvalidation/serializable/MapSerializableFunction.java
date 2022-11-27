package jfluentvalidation.serializable;

import jfluentvalidation.SerializableFunction;

import java.util.Map;

public interface MapSerializableFunction<T, K, V> extends SerializableFunction<T, Map<K, V>> {
}
