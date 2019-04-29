package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.ComparableConstraints;
import jfluentvalidation.constraints.uri.*;
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

    public UriSubject<T> hasPath(String expected) {
        rule.addConstraint(new HasPathConstraint<>(expected));
        return myself;
    }

    public UriSubject<T> hasNoPath(String expected) {
        rule.addConstraint(new HasPathConstraint<>(""));
        return myself;
    }

    public UriSubject<T> hasPort(int expected) {
        rule.addConstraint(new HasPortConstraint<>(expected));
        return myself;
    }

    public UriSubject<T> hasNoPort(int expected) {
        rule.addConstraint(new HasPortConstraint<>(-1));
        return myself;
    }

    public UriSubject<T> hasHost(String expected) {
        rule.addConstraint(new HasHostConstraint<>(expected));
        return myself;
    }

    public UriSubject<T> hasAuthority(String expected) {
        rule.addConstraint(new HasAuthorityConstraint<>(expected));
        return myself;
    }

    public UriSubject<T> hasQuery(String expected) {
        rule.addConstraint(new HasQueryConstraint<>(expected));
        return myself;
    }

    public UriSubject<T> hasNoQuery() {
        rule.addConstraint(new HasQueryConstraint<>(null));
        return myself;
    }

    public UriSubject<T> hasParameter(String name) {
        rule.addConstraint(new HasParameterConstraint<>(name));
        return myself;
    }

    public UriSubject<T> hasParameter(String name, String value) {
        rule.addConstraint(new HasParameterConstraint<>(name, value));
        return myself;
    }

    public UriSubject<T> hasNoParameters() {
        rule.addConstraint(new HasNoParameterConstraint<>());
        return myself;
    }

    public UriSubject<T> hasNoParameters(String name) {
        rule.addConstraint(new HasNoParameterConstraint<>(name));
        return myself;
    }

    public UriSubject<T> hasNoParameters(String name, String value) {
        rule.addConstraint(new HasNoParameterConstraint<>(name, value));
        return myself;
    }

    @Override
    public UriSubject<T> isEqualAccordingToCompareTo(URI other) {
        rule.addConstraint(ComparableConstraints.isEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public UriSubject<T> isNotEqualAccordingToCompareTo(URI other) {
        rule.addConstraint(ComparableConstraints.isNotEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public UriSubject<T> isLessThan(URI other) {
        rule.addConstraint(ComparableConstraints.isLessThan(other));
        return myself;
    }

    @Override
    public UriSubject<T> isLessThanOrEqualTo(URI other) {
        rule.addConstraint(ComparableConstraints.isLessThanOrEqualTo(other));
        return myself;
    }

    @Override
    public UriSubject<T> isGreaterThan(URI other) {
        rule.addConstraint(ComparableConstraints.isGreaterThan(other));
        return myself;
    }

    @Override
    public UriSubject<T> isGreaterThanOrEqualTo(URI other) {
        rule.addConstraint(ComparableConstraints.isGreaterThanOrEqualTo(other));
        return myself;
    }

    @Override
    public UriSubject<T> isBetween(URI startInclusive, URI endInclusive) {
        return myself;
    }

    @Override
    public UriSubject<T> isStrictlyBetween(URI startExclusive, URI endExclusive) {
        rule.addConstraint(ComparableConstraints.isStrictlyBetween(startExclusive, endExclusive));
        return myself;
    }

    @Override
    public UriSubject<T> isBetween(URI start, URI end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public UriSubject<T> isNotBetween(URI startInclusive, URI endInclusive) {
        // rule.addConstraint(ComparableConstraints.isNotBetween());
        return myself;
    }

    @Override
    public UriSubject<T> isNotBetween(URI start, URI end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
