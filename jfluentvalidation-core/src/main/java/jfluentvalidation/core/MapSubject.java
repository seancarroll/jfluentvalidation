package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.MapItemConstraint;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.map.*;
import jfluentvalidation.rules.MapPropertyRule;
import jfluentvalidation.rules.PropertyRule;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @param <T>  the type of the instance
 * @param <K>
 * @param <V>
 */
public class MapSubject<T, K, V> extends Subject<MapSubject<T, K, V>, T, Map<K, V>> {

    public MapSubject(PropertyRule<T, Map<K, V>> rule) {
        super(MapSubject.class, rule);
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> isEmpty() {
        rule.addConstraint(new IsEmptyConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyConstraint<>());
        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> hasSize(int expectedSize) {
        rule.addConstraint(new HasSizeConstraint<>(expectedSize));
        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsKey(K key) {
        rule.addConstraint(new ContainsKeyConstraint<>(key));
        return myself;
    }

    @CanIgnoreReturnValue
    // TODO: add overload which takes in Map.Entry?
    public final MapSubject<T, K, V> containsEntry(K key, V value) {
        rule.addConstraint(new ContainsEntryConstraint<>(new AbstractMap.SimpleEntry<>(key, value)));
        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> doesNotContainsEntry(Object key, Object value) {

        return myself;
    }

    @CanIgnoreReturnValue
    // Fails if the map is not empty...This seems strange
    public final MapSubject<T, K, V> containsExactly() {
        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsExactly(Object key, Object value, Object... rest) {
        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsExactlyEntriesIn(Map<?, ?> expectedMap) {

        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsExactlyEntriesInAnyOrder(Map<?, ?> expected) {

        return myself;
    }

    @CanIgnoreReturnValue
    // TODO: review Google Truth MapSubject MapDifference
    public final MapSubject<T, K, V> doesNotContainEntry(Object key, Object value) {

        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsValue(V value) {
        rule.addConstraint(new ContainsValueConstraint<>(value));
        return myself;
    }

    // TODO: add methods
    // doesNotContainKey
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


    @Override
    public MapSubject<T, K, V> isNull() {
        return super.isNull();
    }

    @Override
    public MapSubject<T, K, V> isNotNull() {
        return super.isNotNull();
    }

    @Override
    public MapSubject<T, K, V> isEquals(Map<K, V> other) {
        return super.isEquals(other);
    }

    /**
     *
     * @param constraintsToAdd
     * @return
     */
    @SafeVarargs
    public final MapSubject<T, K, V> forEachEntry(Constraint<T, Map.Entry<K, V>>... constraintsToAdd) {
        Function<Map<K, V>, Collection<Map.Entry<K, V>>> fks = Map::entrySet;
        for (Constraint<T, Map.Entry<K, V>> constraint : constraintsToAdd) {
            getRule().addItemConstraint(new MapItemConstraint<>(fks, constraint));
        }
        return myself;
    }

//    /**
//     *
//     * @param constraintsToAdd
//     * @return
//     */
//    @SafeVarargs
//    public final MapSubject<T, K, V> forEachEntry(Constraint<T, Map.Entry<K, V>>... constraintsToAdd) {
//        rule.addConstraint(new EntryConstraint<>(constraintsToAdd));
//        return myself;
//    }

    /**
     *
     * @param constraintsToAdd
     * @return
     */
    @SafeVarargs
    public final MapSubject<T, K, V> forEachKey(Constraint<T, K>... constraintsToAdd) {
        Function<Map<K, V>, Collection<K>> fks = Map::keySet;
        for (Constraint<T, K> constraint : constraintsToAdd) {
            getRule().addItemConstraint(new MapItemConstraint<>(fks, constraint));
        }
        return myself;
    }

//    /**
//     *
//     * @param predicate
//     * @param constraintsToAdd
//     * @return
//     */
//    public final MapSubject<T, K, V> forEachKey(Predicate<? super K> predicate, Constraint<?, ? super K>... constraintsToAdd) {
//        // TODO: fix generic
//        rule.addConstraint(new KeyConstraint(predicate, constraintsToAdd));
//        return myself;
//    }

    /**
     *
     * @param constraintsToAdd
     * @return
     */
    @SafeVarargs
    public final MapSubject<T, K, V> forEachValue(Constraint<T, V>... constraintsToAdd) {
        Function<Map<K, V>, Collection<V>> fks = Map::values;
        for (Constraint<T, V> constraint : constraintsToAdd) {
            getRule().addItemConstraint(new MapItemConstraint<>(fks, constraint));
        }
        return myself;
    }

//    /**
//     *
//     * @param constraintsToAdd
//     * @return
//     */
//    @SafeVarargs
//    public final MapSubject<T, K, V> forEachValue(Constraint<T, V>... constraintsToAdd) {
//        rule.addConstraint(new ValueConstraint<>(constraintsToAdd));
//        return myself;
//    }

//    /**
//     *
//     * @param predicate
//     * @param constraintsToAdd
//     * @return
//     */
//    public final MapSubject<K, V> forEachValue(Predicate<? super V> predicate, Constraint<?, ? super V>... constraintsToAdd) {
//        // TODO: fix unchecked warning
//        rule.addConstraint(new ValueConstraint(constraintsToAdd));
//        return myself;
//    }

    @Override
    protected MapPropertyRule<T, K, V> getRule() {
        return (MapPropertyRule<T,K, V>) super.getRule();
    }
}
