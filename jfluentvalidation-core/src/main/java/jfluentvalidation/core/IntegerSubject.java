package jfluentvalidation.core;

import java.util.function.Function;

// TODO: what should the type hierarchy be?
public class IntegerSubject extends AbstractComparableNumber<IntegerSubject, Integer> {

    public IntegerSubject(Function func, String propertyName) {
        super(IntegerSubject.class, func, propertyName);
    }

}
