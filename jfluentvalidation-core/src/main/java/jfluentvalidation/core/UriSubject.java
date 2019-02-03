package jfluentvalidation.core;

import java.net.URI;
import java.util.function.Function;

public class UriSubject extends Subject<UriSubject, URI> implements ComparableSubject<UriSubject, URI> {

    public UriSubject(Function propertyFunc, String propertyName) {
        super(UriSubject.class, propertyFunc, propertyName);
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
