package jfluentvalidation.core;

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
        // rule.addConstraint();
        return myself;
    }

    // hasPath(string expected)
    // hasNoPath()
    // hasPort(int expected)
    // hasNoPort()
    // hasHost(string expected)
    // hasAuthority(string expected)
    // hasQuery(String expected)
    // hasNoQuery()
    // hasAnchor(string expected)
    // hasNoAnchor
    // hasParameter(String name)
    // hasParameter(String name, String value)
    // hasNoParameters()
    // hasNoParameter(String name)
    // hasNoParameter(String name, String value)
}
