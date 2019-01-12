package jfluentvalidation.core;

import java.util.function.Function;

public class CharSequenceSubject<S extends CharSequenceSubject<S, T>, T extends CharSequence> extends Subject<S, T> {

    public CharSequenceSubject(Class<?> selfType, T actual, Function<Object, T> func) {
        super(selfType, actual, func);
    }

}
