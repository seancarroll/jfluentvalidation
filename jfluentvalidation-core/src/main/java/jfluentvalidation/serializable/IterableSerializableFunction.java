package jfluentvalidation.serializable;

import jfluentvalidation.SerializableFunction;

public interface IterableSerializableFunction<T, E> extends SerializableFunction<T, Iterable<? super E>> {
}
