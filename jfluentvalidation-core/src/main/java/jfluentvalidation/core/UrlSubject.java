package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.constraints.url.*;
import jfluentvalidation.rules.PropertyRule;

import java.net.URL;

/**
 * Constraints for {@link URL} subjects.
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
    @CanIgnoreReturnValue
    public UrlSubject<T> hasProtocol(String expected) {
        rule.addConstraint(new HasProtocolConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has the expected path.
     *
     * @param expected  the expected path of the actual {@code URL}.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasPath(String expected) {
        rule.addConstraint(new HasPathConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has no path.
     *
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasNoPath() {
        rule.addConstraint(new HasPathConstraint<>(""));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has the expected port.
     *
     * @param expected  the expected port of the actual {@code URL}.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasPort(int expected) {
        rule.addConstraint(new HasPortConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has no port.
     *
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasNoPort() {
        rule.addConstraint(new HasPortConstraint<>(-1));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has the expected host.
     *
     * @param expected  the expected host of the actual {@code URL}.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasHost(String expected) {
        rule.addConstraint(new HasHostConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has the expected authority.
     *
     * @param expected  the expected authority of the actual {@code URL}.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasAuthority(String expected) {
        rule.addConstraint(new HasAuthorityConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has the expected query.
     *
     * @param expected  the expected query of the actual {@code URL}.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasQuery(String expected) {
        rule.addConstraint(new HasQueryConstraint<>(expected));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has no query.
     *
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasNoQuery() {
        rule.addConstraint(new HasQueryConstraint<>(null));
        return myself;
    }

    /**
     *
     * @param expected
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
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
     * Verifies that the actual {@code URL} has a parameter with the expected name.
     *
     * @param name  the name of the parameter expected to be present.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasParameter(String name) {
        rule.addConstraint(new HasParameterConstraint<>(name));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has a parameter with the expected name and value.
     *
     * @param name  the name of the parameter expected to be present.
     * @param value  the value of the parameter expected to be present.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasParameter(String name, String value) {
        rule.addConstraint(new HasParameterConstraint<>(name, value));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} does not have any parameters.
     *
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasNoParameters() {
        rule.addConstraint(new HasNoParameterConstraint<>());
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} does not have a parameter with the specified name.
     *
     * @param name  the name of the parameter expected to be absent.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasNoParameters(String name) {
        rule.addConstraint(new HasNoParameterConstraint<>(name));
        return myself;
    }

    /**
     * Verifies that the actual {@code URL} has a parameter with the expected name and value.
     *
     * @param name  the name of the parameter expected to be present.
     * @param value  the value of the parameter expected to be present.
     * @return {@code this} Url subject.
     */
    @CanIgnoreReturnValue
    public UrlSubject<T> hasNoParameters(String name, String value) {
        rule.addConstraint(new HasNoParameterConstraint<>(name, value));
        return myself;
    }

}
