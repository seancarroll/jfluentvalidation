package jfluentvalidation.core;

import jfluentvalidation.constraints.url.*;
import jfluentvalidation.rules.PropertyRule;

import java.net.URL;

/**
 *
 * @param <T>  the type of the instance
 */
public class UrlSubject<T> extends Subject<UrlSubject<T>, T, URL> {

    public UrlSubject(PropertyRule<T, URL> rule) {
        super(UrlSubject.class, rule);
    }

    public UrlSubject<T> hasProtocol(String expected) {
        rule.addConstraint(new HasProtocolConstraint<>(expected));
        return myself;
    }

    public UrlSubject<T> hasPath(String expected) {
        rule.addConstraint(new HasPathConstraint<>(expected));
        return myself;
    }

    public UrlSubject<T> hasNoPath(String expected) {
        rule.addConstraint(new HasPathConstraint<>(""));
        return myself;
    }

    public UrlSubject<T> hasPort(int expected) {
        rule.addConstraint(new HasPortConstraint<>(expected));
        return myself;
    }

    public UrlSubject<T> hasNoPort(int expected) {
        rule.addConstraint(new HasPortConstraint<>(-1));
        return myself;
    }

    public UrlSubject<T> hasHost(String expected) {
        rule.addConstraint(new HasHostConstraint<>(expected));
        return myself;
    }

    public UrlSubject<T> hasAuthority(String expected) {
        rule.addConstraint(new HasAuthorityConstraint<>(expected));
        return myself;
    }

    public UrlSubject<T> hasQuery(String expected) {
        rule.addConstraint(new HasQueryConstraint<>(expected));
        return myself;
    }

    public UrlSubject<T> hasNoQuery() {
        rule.addConstraint(new HasQueryConstraint<>(null));
        return myself;
    }

    public UrlSubject<T> hasAnchor(String expected) {
        rule.addConstraint(new HasAnchorConstraint<>(expected));
        return myself;
    }

    public UrlSubject<T> hasNoAnchor() {
        rule.addConstraint(new HasAnchorConstraint<>(null));
        return myself;
    }

    public UrlSubject<T> hasParameter(String name) {
        rule.addConstraint(new HasParameterConstraint<>(name));
        return myself;
    }

    public UrlSubject<T> hasParameter(String name, String value) {
        rule.addConstraint(new HasParameterConstraint<>(name, value));
        return myself;
    }

    public UrlSubject<T> hasNoParameters() {
        rule.addConstraint(new HasNoParameterConstraint<>());
        return myself;
    }

    public UrlSubject<T> hasNoParameters(String name) {
        rule.addConstraint(new HasNoParameterConstraint<>(name));
        return myself;
    }

    public UrlSubject<T> hasNoParameters(String name, String value) {
        rule.addConstraint(new HasNoParameterConstraint<>(name, value));
        return myself;
    }

}
