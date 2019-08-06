package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.comparable.ComparableConstraints;
import jfluentvalidation.constraints.uri.*;
import jfluentvalidation.rules.PropertyRule;

import java.net.URI;

/**
 * Constraints for {@link URI} subjects.
 *
 * @param <T>  the type of the instance.
 * @see java.net.URI
 */
public class UriSubject<T> extends Subject<UriSubject<T>, T, URI> implements ComparableSubject<UriSubject<T>, T, URI> {

    public UriSubject(PropertyRule<T, URI> rule) {
        super(UriSubject.class, rule);
    }

    /**
     * Verifies that the actual {@code URI} has the expected path.
     *
     * @param expected  the expected path of the actual {@code URI}.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasPath(String expected) {
        rule.addConstraint(new HasPathConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has no path.
     *
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasNoPath() {
        rule.addConstraint(new HasPathConstraint<>(""));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has the expected port.
     *
     * @param expected  the expected port of the actual {@code URI}.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasPort(int expected) {
        rule.addConstraint(new HasPortConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has no port.
     *
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasNoPort() {
        rule.addConstraint(new HasPortConstraint<>(-1));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has the expected host.
     *
     * @param expected  the expected host of the actual {@code URI}.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasHost(String expected) {
        rule.addConstraint(new HasHostConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has the expected authority.
     *
     * @param expected  the expected authority of the actual {@code URI}.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasAuthority(String expected) {
        rule.addConstraint(new HasAuthorityConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has the expected query.
     *
     * @param expected  the expected query of the actual {@code URI}.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasQuery(String expected) {
        rule.addConstraint(new HasQueryConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has no query.
     *
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasNoQuery() {
        rule.addConstraint(new HasQueryConstraint<>(null));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has a parameter with the expected name.
     *
     * @param name  the name of the parameter expected to be present.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasParameter(String name) {
        rule.addConstraint(new HasParameterConstraint<>(name));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has a parameter with the expected name and value.
     *
     * @param name  the name of the parameter expected to be present.
     * @param value  the value of the parameter expected to be present.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasParameter(String name, String value) {
        rule.addConstraint(new HasParameterConstraint<>(name, value));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} does not have any parameters.
     *
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasNoParameters() {
        rule.addConstraint(new HasNoParameterConstraint<>());
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} does not have a parameter with the specified name.
     *
     * @param name  the name of the parameter expected to be absent.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
    public UriSubject<T> hasNoParameters(String name) {
        rule.addConstraint(new HasNoParameterConstraint<>(name));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} does not have a parameter with the expected name and value.
     *
     * @param name  the name of the parameter expected to be absent.
     * @param value  the value of the parameter expected to be absent.
     * @return {@code this} Uri subject.
     */
    @CanIgnoreReturnValue
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
        rule.addConstraint(ComparableConstraints.isNotBetween(startInclusive, endInclusive, true, true));
        return myself;
    }

    @Override
    public UriSubject<T> isNotBetween(URI start, URI end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
