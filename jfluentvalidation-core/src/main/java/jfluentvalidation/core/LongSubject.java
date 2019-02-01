package jfluentvalidation.core;

import java.util.function.Function;

public class LongSubject extends AbstractComparableNumber { // Subject<LongSubject, Long> implements NumberSubject<LongSubject, Long>, ComparableSubject<LongSubject, Long> {

    public LongSubject(Function propertyFunc, String propertyName) {
        super(LongSubject.class, propertyFunc, propertyName);
    }

}
