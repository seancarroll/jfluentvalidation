package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class LongSubject<T> extends AbstractComparableNumber<LongSubject<T>, T, Long> {

    public LongSubject(PropertyRule<T, Long> rule) {
        super(LongSubject.class, rule);
    }
}
