package jfluentvalidation.core;

import java.util.function.Function;

abstract class AbstractArraySubject<S extends AbstractArraySubject<S, T>, T> extends Subject<S, T> {

    public AbstractArraySubject(Class<?> selfType, Function<Object, T> propertyFunc, String propertyName) {
        super(selfType, propertyFunc, propertyName);
    }

}
