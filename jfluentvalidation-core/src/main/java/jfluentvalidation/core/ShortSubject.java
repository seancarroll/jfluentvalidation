package jfluentvalidation.core;

import java.util.function.Function;

public class ShortSubject extends AbstractComparableNumber<ShortSubject, Short> {

    public ShortSubject(Function propertyFunc, String propertyName) {
        super(ShortSubject.class, propertyFunc, propertyName);
    }
}
