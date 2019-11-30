package jfluentvalidation.internal;

import jfluentvalidation.ValidationFailure;

import java.util.ArrayList;
import java.util.List;

public final class Empty {

    public static final String[] STRING_ARRAY = new String[0];

    // TODO: should we just use Collections.EMPTY_LIST; here?
    public static final List<ValidationFailure> VALIDATION_FAILURES = new ArrayList<>(0);

    private Empty() {
        // statics only
    }

}
