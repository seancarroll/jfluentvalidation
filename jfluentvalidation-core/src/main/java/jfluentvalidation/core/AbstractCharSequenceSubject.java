package jfluentvalidation.core;

import java.util.function.Function;

public abstract class AbstractCharSequenceSubject<S extends AbstractCharSequenceSubject<S, A>, A extends CharSequence>
    extends Subject<S, A> {

    public AbstractCharSequenceSubject(Class<?> selfType, Function<Object, A> propertyFunc, String propertyName) {
        super(selfType, propertyFunc, propertyName);
    }

}
