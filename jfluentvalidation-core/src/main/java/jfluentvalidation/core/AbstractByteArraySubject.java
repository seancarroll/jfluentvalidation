package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

public abstract class AbstractByteArraySubject<S extends AbstractByteArraySubject<S, T>, T>
    extends AbstractArraySubject<S, T, byte[], Byte> {

    public AbstractByteArraySubject(Class<?> selfType, PropertyRule<T, byte[]> rule) {
        super(selfType, rule);
    }

//    public S contains(byte item) {
//        rule.addConstraint(new ContainsConstraint<T, Byte>(item));
//        return myself;
//    }
}
