package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.*;
import jfluentvalidation.rules.PropertyRule;

import java.io.File;

/**
 *
 * @param <T>  the type of the instance
 */
public class FileSubject<T> extends Subject<FileSubject<T>, T, File> implements ComparableSubject<FileSubject<T>, T, File> {

    public FileSubject(PropertyRule<T, File> rule) {
        super(FileSubject.class, rule);
    }

    @Override
    public FileSubject<T> isEqualAccordingToCompareTo(File other) {
        rule.addConstraint(new IsEqualAccordingToCompareToConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isNotEqualAccordingToCompareTo(File other) {
        rule.addConstraint(new IsNotEqualAccordingToCompareToConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isLessThan(File other) {
        rule.addConstraint(new IsLessThanConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isLessThanOrEqualTo(File other) {
        rule.addConstraint(new IsLessThanOrEqualToConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isGreaterThan(File other) {
        rule.addConstraint(new IsGreaterThanConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isGreaterThanOrEqualTo(File other) {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint<>(other));
        return myself;
    }

    @Override
    public FileSubject<T> isBetween(File startInclusive, File endInclusive) {
        rule.addConstraint(new IsBetweenConstraint<>(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public FileSubject<T> isStrictlyBetween(File startExclusive, File endExclusive) {
        rule.addConstraint(new IsBetweenConstraint<>(startExclusive, endExclusive, true, true));
        return myself;
    }

    @Override
    public FileSubject<T> isBetween(File start, File end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IsBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public FileSubject<T> isNotBetween(File startInclusive, File endInclusive) {
        rule.addConstraint(new IsNotBetweenConstraint<>(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public FileSubject<T> isNotBetween(File start, File end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(new IsNotBetweenConstraint(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
