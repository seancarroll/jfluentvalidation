package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.io.File;

public class FileSubject<T> extends Subject<FileSubject<T>, T, File> implements ComparableSubject<FileSubject<T>, T, File> {

    public FileSubject(PropertyRule<T, File> rule) {
        super(FileSubject.class, rule);
    }

    @Override
    public FileSubject<T> isEqualAccordingToCompareTo(File other) {
        return null;
    }

    @Override
    public FileSubject<T> isNotEqualAccordingToCompareTo(File other) {
        return null;
    }

    @Override
    public FileSubject<T> isLessThan(File other) {
        return null;
    }

    @Override
    public FileSubject<T> isLessThanOrEqualTo(File other) {
        return null;
    }

    @Override
    public FileSubject<T> isGreaterThan(File other) {
        return null;
    }

    @Override
    public FileSubject<T> isGreaterThanOrEqualTo(File other) {
        return null;
    }

    @Override
    public FileSubject<T> isBetween(File startInclusive, File endInclusive) {
        return null;
    }

    @Override
    public FileSubject<T> isStrictlyBetween(File startExclusive, File endExclusive) {
        return null;
    }

    @Override
    public FileSubject<T> isBetween(File start, File end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public FileSubject<T> isNotBetween(File startInclusive, File endInclusive) {
        return null;
    }

    @Override
    public FileSubject<T> isNotBetween(File start, File end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
