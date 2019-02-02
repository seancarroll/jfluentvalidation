package jfluentvalidation.core;

import java.util.function.Function;

public class LongSubject extends AbstractComparableNumber<LongSubject, Long> {

    public LongSubject(Function propertyFunc, String propertyName) {
        super(LongSubject.class, propertyFunc, propertyName);
    }

}
