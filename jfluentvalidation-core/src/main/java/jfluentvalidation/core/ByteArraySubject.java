package jfluentvalidation.core;

import java.util.function.Function;

// TODO: We could potentially have many different types of array
// what makes sense to include? assertj has the following:
// BooleanArray, CharArray, DoubleArray, FloatArray, IntArray, ObjectArray, ShortArray
// this could extend an AbstractArraySubject
public class ByteArraySubject extends Subject<ByteArraySubject, byte[]> {

    public ByteArraySubject(Function propertyFunc, String propertyName) {
        super(ByteArraySubject.class, propertyFunc, propertyName);
    }
}
