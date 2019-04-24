package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>
 */
public class LongSubject<T> extends AbstractComparableNumber<LongSubject<T>, T, Long> {

    public LongSubject(PropertyRule<T, Long> rule) {
        super(LongSubject.class, rule);
    }

    @Override
    protected Long zero() {
        return 0L;
    }

    @Override
    protected Long one() {
        return 1L;
    }
}
