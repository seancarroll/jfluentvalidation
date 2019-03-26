package jfluentvalidation.core;

import java.util.function.Function;

public class ObjectSubject extends Subject<ObjectSubject, Object> {

    public ObjectSubject(Function propertyFunc, String propertyName) {
        super(ObjectSubject.class, propertyFunc, propertyName);
    }
}
