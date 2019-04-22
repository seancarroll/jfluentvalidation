package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class StringSubject
    extends AbstractCharSequenceSubject<StringSubject, String>
    implements ComparableSubject<StringSubject, String> {

    public StringSubject(PropertyRule<?, String> rule) {
        super(StringSubject.class, rule);
    }

    @Override
    public StringSubject isEqualAccordingToCompareTo(String other) {
        return myself;
    }

    @Override
    public StringSubject isNotEqualAccordingToCompareTo(String other) {
        return myself;
    }

    @Override
    public StringSubject isLessThan(String other) {
        return myself;
    }

    @Override
    public StringSubject isLessThanOrEqualTo(String other) {
        return myself;
    }

    @Override
    public StringSubject isGreaterThan(String other) {
        return myself;
    }

    @Override
    public StringSubject isGreaterThanOrEqualTo(String other) {
        return myself;
    }

    @Override
    public StringSubject isBetween(String startInclusive, String endInclusive) {
        return myself;
    }

    @Override
    public StringSubject isStrictlyBetween(String startExclusive, String endExclusive) {
        return myself;
    }

    @Override
    public StringSubject isBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public StringSubject isNotBetween(String startInclusive, String endInclusive) {
        return myself;
    }

    @Override
    public StringSubject isNotBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }


}
