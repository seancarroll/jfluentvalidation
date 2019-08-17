package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 * Assertion class for {@link java.net.URI}s
 *
 * @param <T>  the type of the instance
 */
public class ByteSubject<T> extends AbstractComparableNumber<ByteSubject<T>, T, Byte> {

    public ByteSubject(PropertyRule<T, Byte> rule) {
        super(ByteSubject.class, rule);
    }

    @Override
    protected Byte zero() {
        return 0;
    }

    @Override
    protected Byte one() {
        return 1;
    }
}
