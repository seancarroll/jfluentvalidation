package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.ComparableConstraints;
import jfluentvalidation.rules.PropertyRule;

/**
 * Constraints for {@link String} subjects.
 *
 * @param <T>  the type of the instance
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
        rule.addConstraint(ComparableConstraints.isBetween(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public StringSubject<T> isStrictlyBetween(String startExclusive, String endExclusive) {
        rule.addConstraint(ComparableConstraints.isBetween(startExclusive, endExclusive, false, false));
        return myself;
    }

    @Override
    public StringSubject<T> isBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public StringSubject<T> isNotBetween(String startInclusive, String endInclusive) {
        rule.addConstraint(ComparableConstraints.isNotBetween(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public StringSubject<T> isNotBetween(String start, String end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
