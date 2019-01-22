package jfluentvalidation.core;

import java.util.function.Function;

public abstract class ComparableSubject<S extends ComparableSubject<S, T>, T extends Comparable>
    extends Subject<S, T> {

    public ComparableSubject(Class<?> selfType, Function<Object, T> func, String propertyName) {
        super(selfType, func, propertyName);
    }
}
