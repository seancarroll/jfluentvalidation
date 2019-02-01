package jfluentvalidation.core;

import java.util.function.Function;

public class FloatSubject extends AbstractComparableNumber<FloatSubject, Float> {

    public FloatSubject(Function propertyFunc, String propertyName) {
        super(FloatSubject.class, propertyFunc, propertyName);
    }
}
