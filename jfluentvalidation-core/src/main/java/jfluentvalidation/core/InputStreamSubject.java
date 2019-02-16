package jfluentvalidation.core;

import java.io.InputStream;
import java.util.function.Function;


public class InputStreamSubject extends Subject<InputStreamSubject, InputStream> {

    public InputStreamSubject(Function propertyFunc, String propertyName) {
        super(InputStreamSubject.class, propertyFunc, propertyName);
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
