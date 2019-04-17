package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.net.URI;

/**
 *
 */
public class UriSubject extends Subject<UriSubject, URI> implements ComparableSubject<UriSubject, URI> {

    public UriSubject(PropertyRule<?, URI> rule) {
        super(UriSubject.class, rule);
    }

    @Override
    public UriSubject isEqualAccordingToCompareTo(URI other) {
        return null;
    }

    @Override
    public UriSubject isNotEqualAccordingToCompareTo(URI other) {
        return null;
    }

    @Override
    public UriSubject isLessThan(URI other) {
        return null;
    }

    @Override
    public UriSubject isLessThanOrEqualTo(URI other) {
        return null;
    }

    @Override
    public UriSubject isGreaterThan(URI other) {
        return null;
    }

    @Override
    public UriSubject isGreaterThanOrEqualTo(URI other) {
        return null;
    }

    @Override
    public UriSubject isBetween(URI startInclusive, URI endInclusive) {
        return null;
    }

    @Override
    public UriSubject isStrictlyBetween(URI startExclusive, URI endExclusive) {
        return null;
    }

    @Override
    public UriSubject isBetween(URI start, URI end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public UriSubject isNotBetween(URI startInclusive, URI endInclusive) {
        return null;
    }

    @Override
    public UriSubject isNotBetween(URI start, URI end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
