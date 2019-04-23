package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: We could potentially have many different types of array
// what makes sense to include? assertj has the following:
// BooleanArray, CharArray, DoubleArray, FloatArray, IntArray, ObjectArray, ShortArray
// this could extend an AbstractArraySubject
public class ByteArraySubject<T> extends Subject<ByteArraySubject<T>, T, byte[]> {

    public ByteArraySubject(PropertyRule<T, byte[]> rule) {
        super(ByteArraySubject.class, rule);
    }

}
