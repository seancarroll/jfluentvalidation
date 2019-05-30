package jfluentvalidation.rules;

import java.util.function.Function;

public class ArrayPropertyRule<T, P> extends PropertyRule<T, P[]> {

    public ArrayPropertyRule(Function<T, P[]> propertyFunc, String propertyName) {
        super(propertyFunc, propertyName);
    }
}
