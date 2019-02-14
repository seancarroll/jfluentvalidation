package jfluentvalidation.common;

import jfluentvalidation.internal.Ensure;

import java.util.Collection;

public final class MoreCollections {

    private MoreCollections() {
        // statics only
    }

    static boolean safeContains(Collection<?> collection, /*@NullableDecl*/ Object object) {
        Ensure.notNull(collection);
        try {
            return collection.contains(object);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }
}
