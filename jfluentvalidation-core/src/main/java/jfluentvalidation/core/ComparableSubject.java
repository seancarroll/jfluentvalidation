package jfluentvalidation.core;

import java.util.function.Function;

public abstract class ComparableSubject<S extends ComparableSubject<S, T>, T extends Comparable>
    extends Subject<S, T> {

    public ComparableSubject(Class<?> selfType, T actual, Function<Object, T> func) {
        super(selfType, actual, func);
    }

    public ComparableSubject(Class<?> selfType, T actual) {
        super(selfType, actual, null);
    }
}
