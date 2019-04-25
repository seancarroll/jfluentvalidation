package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class StringSubject<T>
    extends AbstractCharSequenceSubject<StringSubject<T>, T, String>
    implements ComparableSubject<StringSubject<T>, T, String> {

    public StringSubject(PropertyRule<T, String> rule) {
        super(StringSubject.class, rule);
    }

    @Override
    public StringSubject<T> isEqualAccordingToCompareTo(String other) {
        return myself;
    }

    @Override
    public StringSubject<T> isNotEqualAccordingToCompareTo(String other) {
        return myself;
    }

    @Override
    public StringSubject<T> isLessThan(String other) {
        return myself;
    }

    @Override
    public StringSubject<T> isLessThanOrEqualTo(String other) {
        return myself;
    }

    @Override
    public StringSubject<T> isGreaterThan(String other) {
        return myself;
    }

    @Override
    public StringSubject<T> isGreaterThanOrEqualTo(String other) {
        return myself;
    }

    @Override
    public StringSubject<T> isBetween(String startInclusive, String endInclusive) {
        return myself;
    }

    @Override
    public StringSubject<T> isStrictlyBetween(String startExclusive, String endExclusive) {
        return myself;
    }

    @Override
    public StringSubject<T> isBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public StringSubject<T> isNotBetween(String startInclusive, String endInclusive) {
        return myself;
    }

    @Override
    public StringSubject<T> isNotBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }


}
