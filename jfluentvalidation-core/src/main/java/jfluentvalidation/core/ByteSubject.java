package jfluentvalidation.core;

import java.util.function.Function;

public class ByteSubject extends AbstractComparableNumber<ByteSubject, Byte> {

    public ByteSubject(Function propertyFunc, String propertyName) {
        super(Byte.class, propertyFunc, propertyName);
    }
}