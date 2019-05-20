package jfluentvalidation.core;

import jfluentvalidation.constraints.url.*;
import jfluentvalidation.rules.PropertyRule;

import java.net.URL;

/**
 *
 * @param <T>  the type of the instance.
 * @see java.net.URL
 */
public class UrlSubject<T> extends Subject<UrlSubject<T>, T, URL> {

    public UrlSubject(PropertyRule<T, URL> rule) {
        super(UrlSubject.class, rule);
    }

    /**
     *
     * @param expected
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasProtocol(String expected) {
        rule.addConstraint(new HasProtocolConstraint<>(expected));
        return myself;
    }

    /**
     *
     * @param expected  the expected path of the actual {@code URL}.
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasPath(String expected) {
        rule.addConstraint(new HasPathConstraint<>(expected));
        return myself;
    }

    /**
     *
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasNoPath() {
        rule.addConstraint(new HasPathConstraint<>(""));
        return myself;
    }

    /**
     *
     * @param expected
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasPort(int expected) {
        rule.addConstraint(new HasPortConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URI} has no port.
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasNoPort() {
        rule.addConstraint(new HasPortConstraint<>(-1));
        return myself;
    }

    /**
     *
     * @param expected
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasHost(String expected) {
        rule.addConstraint(new HasHostConstraint<>(expected));
        return myself;
    }

    /**
     *
     * @param expected
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasAuthority(String expected) {
        rule.addConstraint(new HasAuthorityConstraint<>(expected));
        return myself;
    }

    /**
     *
     * @param expected
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasQuery(String expected) {
        rule.addConstraint(new HasQueryConstraint<>(expected));
        return myself;
    }

    /**
     *
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasNoQuery() {
        rule.addConstraint(new HasQueryConstraint<>(null));
        return myself;
    }

    /**
     *
     * @param expected
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasAnchor(String expected) {
        rule.addConstraint(new HasAnchorConstraint<>(expected));
        return myself;
    }

    /**
     *
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasNoAnchor() {
        rule.addConstraint(new HasAnchorConstraint<>(null));
        return myself;
    }

    /**
     *
     * @param name
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasParameter(String name) {
        rule.addConstraint(new HasParameterConstraint<>(name));
        return myself;
    }

    /**
     *
     * @param name
     * @param value
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasParameter(String name, String value) {
        rule.addConstraint(new HasParameterConstraint<>(name, value));
        return myself;
    }

    /**
     *
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasNoParameters() {
        rule.addConstraint(new HasNoParameterConstraint<>());
        return myself;
    }

    /**
     *
     * @param name
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasNoParameters(String name) {
        rule.addConstraint(new HasNoParameterConstraint<>(name));
        return myself;
    }

    /**
     *
     * @param name
     * @param value
     * @return {@code this} Url subject.
     */
    public UrlSubject<T> hasNoParameters(String name, String value) {
        rule.addConstraint(new HasNoParameterConstraint<>(name, value));
        return myself;
    }

}
