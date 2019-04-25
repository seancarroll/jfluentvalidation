package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.io.InputStream;

/**
 *
 */
public class InputStreamSubject<T> extends Subject<InputStreamSubject<T>, T, InputStream> {

    public InputStreamSubject(PropertyRule<T, InputStream> rule) {
        super(InputStreamSubject.class, rule);
    }

    public InputStreamSubject hasSameContentAs(InputStream expected) {
        // TODO: implement
        //inputStreams.assertSameContentAs(info, actual, expected);
        return myself;
    }

    public InputStreamSubject hasContent(String expected) {
        // TODO: implement
        //inputStreams.assertHasContent(info, actual, expected);
        return myself;
    }

}
