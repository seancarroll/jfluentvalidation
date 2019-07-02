package jfluentvalidation.core;

import jfluentvalidation.constraints.inputstream.HasContentConstraint;
import jfluentvalidation.constraints.inputstream.HasSameContentAsConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.io.InputStream;

/**
 *
 * @param <T>  the type of the instance
 * @see java.io.InputStream
 */
public class InputStreamSubject<T> extends Subject<InputStreamSubject<T>, T, InputStream> {

    public InputStreamSubject(PropertyRule<T, InputStream> rule) {
        super(InputStreamSubject.class, rule);
    }

    public InputStreamSubject hasSameContentAs(InputStream expected) {
        rule.addConstraint(new HasSameContentAsConstraint<>(expected));
        return myself;
    }

    public InputStreamSubject hasContent(String expected) {
        rule.addConstraint(new HasContentConstraint<>(expected));
        return myself;
    }

}
