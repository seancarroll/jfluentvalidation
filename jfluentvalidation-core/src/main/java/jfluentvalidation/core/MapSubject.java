package jfluentvalidation.core;

import jfluentvalidation.constraints.map.ContainsValueConstraint;
import jfluentvalidation.constraints.map.HasSizeConstraint;
import jfluentvalidation.constraints.map.IsEmptyConstraint;
import jfluentvalidation.constraints.map.IsNotEmptyConstraint;

import java.util.Map;
import java.util.function.Function;

public class MapSubject<K, V> extends Subject<MapSubject<K, V>, Map<K, V>> {

    public MapSubject(Function propertyFunc, String propertyName) {
        super(MapSubject.class, propertyFunc, propertyName);
    }

    public final MapSubject isEmpty() {
        constraints.add(new IsEmptyConstraint());
        return myself;
    }

    public final MapSubject isNotEmpty() {
        constraints.add(new IsNotEmptyConstraint());
        return myself;
    }

    public final MapSubject hasSize(int expectedSize) {
        constraints.add(new HasSizeConstraint(expectedSize));
        return myself;
    }

    public final MapSubject containsKey(Object key) {

        return myself;
    }

    public final MapSubject containsEntry(Object key, Object value) {

        return myself;
    }

    public final MapSubject doesNotContainsEntry(Object key, Object value) {

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




    public final MapSubject containsValue(V value) {
        constraints.add(new ContainsValueConstraint(value));
        return myself;
    }


    // doesNotContainsKey
    // doesNotContainKeys
    // containsOnlyKeys
    // containsValue
    // containsValues
    // doesNotContainValue
    // containsOnly(Map.Entry<? extends K, ? extends V>... entries)
    // isEqualTo
    // isIn(Iterable<?> values)
    // isIn(Object... values)
    // isNotEqualTo
    // isNotIn(Iterable<?> values)
    // isNotIn(Object...values)
    // isNotNull
    // hasEntrySatisfying(K key, Condition<? super V> valueCondition)
    // hasEntrySatisfying(K key, Consumer<? super V> valueRequirements)
    // hasEntrySatisfying(Condition<? super Map.Entry<K, V>> entryCondition)
    // hasEntrySatisfying(Condition<? super K> keyCondition, Condition<? super V> valueCondition)
    // hasKeySatisfying(Condition<? super K> keyCondition)
    // hasValueSatisfying(Condition<? super V> valueCondition)
}
