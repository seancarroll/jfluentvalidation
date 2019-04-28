package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.net.URI;

/**
 *
 * @param <T>  the type of the instance
 */
public class UriSubject<T> extends Subject<UriSubject<T>, T, URI> implements ComparableSubject<UriSubject<T>, T, URI> {

    public UriSubject(PropertyRule<T, URI> rule) {
        super(UriSubject.class, rule);
    }

    @Override
    public UriSubject<T> isEqualAccordingToCompareTo(URI other) {
        return null;
    }

    @Override
    public UriSubject<T> isNotEqualAccordingToCompareTo(URI other) {
        return null;
    }

    @Override
    public UriSubject<T> isLessThan(URI other) {
        return null;
    }

    @Override
    public UriSubject<T> isLessThanOrEqualTo(URI other) {
        return null;
    }

    @Override
    public UriSubject<T> isGreaterThan(URI other) {
        return null;
    }

    @Override
    public UriSubject<T> isGreaterThanOrEqualTo(URI other) {
        return null;
    }

    @Override
    public UriSubject<T> isBetween(URI startInclusive, URI endInclusive) {
        return null;
    }

    @Override
    public UriSubject<T> isStrictlyBetween(URI startExclusive, URI endExclusive) {
        return null;
    }

    @Override
    public UriSubject<T> isBetween(URI start, URI end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public UriSubject<T> isNotBetween(URI startInclusive, URI endInclusive) {
        return null;
    }

    @Override
    public UriSubject<T> isNotBetween(URI start, URI end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
