package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.io.InputStream;

/**
 *
 */
public class InputStreamSubject extends Subject<InputStreamSubject, InputStream> {

    public InputStreamSubject(PropertyRule<?, InputStream> rule) {
        super(InputStreamSubject.class, rule);
    }

    public InputStreamSubject hasSameContentAs(InputStream expected) {
        //inputStreams.assertSameContentAs(info, actual, expected);
        return myself;
    }

    public InputStreamSubject hasContent(String expected) {
        //inputStreams.assertHasContent(info, actual, expected);
        return myself;
    }

}
