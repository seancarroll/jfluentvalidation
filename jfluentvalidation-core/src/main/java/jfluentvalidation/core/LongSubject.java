package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class LongSubject extends AbstractComparableNumber<LongSubject, Long> {

    public LongSubject(PropertyRule<?, Long> rule) {
        super(LongSubject.class, rule);
    }
}
