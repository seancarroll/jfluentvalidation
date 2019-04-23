package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class ByteSubject<T> extends AbstractComparableNumber<ByteSubject<T>, T, Byte> {

    public ByteSubject(PropertyRule<T, Byte> rule) {
        super(ByteSubject.class, rule);
    }
}
