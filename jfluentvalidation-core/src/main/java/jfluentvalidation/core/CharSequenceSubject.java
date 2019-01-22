package jfluentvalidation.core;

import java.util.function.Function;

public class CharSequenceSubject<S extends CharSequenceSubject<S, T>, T extends CharSequence> extends Subject<S, T> {

    public CharSequenceSubject(Class<?> selfType, Function<Object, T> func, String propertyName) {
        super(selfType, func, propertyName);
    }

}
