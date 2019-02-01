package jfluentvalidation.core;

import java.util.function.Function;

public class DoubleSubject extends AbstractComparableNumber<DoubleSubject, Double> {

    public DoubleSubject(Function propertyFunc, String propertyName) {
        super(DoubleSubject.class, propertyFunc, propertyName);
    }
}
