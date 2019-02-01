package jfluentvalidation.core;

import java.util.function.Function;

public class DoubleSubject extends AbstractComparableNumber {

    public DoubleSubject(Function propertyFunc, String propertyName) {
        super(DoubleSubject.class, propertyFunc, propertyName);
    }
}
