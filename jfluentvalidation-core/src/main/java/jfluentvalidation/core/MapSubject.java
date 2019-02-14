package jfluentvalidation.core;

import java.util.Map;
import java.util.function.Function;

public class MapSubject extends Subject<MapSubject, Map<?, ?>> {

    public MapSubject(Function propertyFunc, String propertyName) {
        super(MapSubject.class, propertyFunc, propertyName);
    }

    public final MapSubject isEmpty() {

        return myself;
    }

    public final MapSubject isNotEmpty() {

        return myself;
    }

    public final MapSubject hasSize() {

        return myself;
    }

    public final MapSubject containsKey(Object key) {

        return myself;
    }

    public final MapSubject containsEnty(Object key, Object value) {

        return myself;
    }

    public final MapSubject doesNotContainsEnty(Object key, Object value) {

        return myself;
    }

    // Fails if the map is not empty...This seems strange
    public final MapSubject containsExactly() {
        return myself;
    }

    public final MapSubject containsExactly(Object key, Object value, Object... rest) {
        return myself;
    }

    public final MapSubject containsExactlyEntriesIn(Map<?, ?> expectedMap) {

        return myself;
    }

    public final MapSubject containsExactlyEntriesInAnyOrder(Map<?, ?> expected) {

        return myself;
    }

    // TODO: review Google Truth MapSubject MapDifference
    public final MapSubject doesNotContainEntry(Object key, Object value) {

        return myself;
    }
}
