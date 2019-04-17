package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class ByteSubject extends AbstractComparableNumber<ByteSubject, Byte> {

    public ByteSubject(PropertyRule<?, Byte> rule) {
        super(ByteSubject.class, rule);
    }
}
