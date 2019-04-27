package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.ComparableConstraints;
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
        rule.addConstraint(ComparableConstraints.isEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public StringSubject<T> isNotEqualAccordingToCompareTo(String other) {
        rule.addConstraint(ComparableConstraints.isNotEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public StringSubject<T> isLessThan(String other) {
        rule.addConstraint(ComparableConstraints.isLessThan(other));
        return myself;
    }

    @Override
    public StringSubject<T> isLessThanOrEqualTo(String other) {
        rule.addConstraint(ComparableConstraints.isLessThanOrEqualTo(other));
        return myself;
    }

    @Override
    public StringSubject<T> isGreaterThan(String other) {
        rule.addConstraint(ComparableConstraints.isGreaterThan(other));
        return myself;
    }

    @Override
    public StringSubject<T> isGreaterThanOrEqualTo(String other) {
        rule.addConstraint(ComparableConstraints.isGreaterThanOrEqualTo(other));
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
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public StringSubject<T> isNotBetween(String startInclusive, String endInclusive) {
        return myself;
    }

    @Override
    public StringSubject<T> isNotBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }


}
