package jfluentvalidation.core;

import jfluentvalidation.constraints.numbers.IsCloseToByteConstraint;
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
    public ByteSubject<T> isCloseTo(Byte expected, Byte offset, boolean strict) {
        rule.addConstraint(new IsCloseToByteConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    public ByteSubject<T> isNotCloseTo(Byte expected, Byte offset, boolean strict) {
        // TODO: implement
        // rule.addConstraint(new IsNotCloseToByteConstraint<>(expected, offset, strict));
        return myself;
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
