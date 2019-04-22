package jfluentvalidation.core;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.map.*;
import jfluentvalidation.rules.PropertyRule;

import java.util.Map;
import java.util.function.Predicate;

/**
 *
 * @param <K>
 * @param <V>
 */
public class MapSubject<K, V> extends Subject<MapSubject<K, V>, Map<K, V>> {

    public MapSubject(PropertyRule<?, Map<K, V>> rule) {
        super(MapSubject.class, rule);
    }

    public final MapSubject<K, V> isEmpty() {
        rule.addConstraint(new IsEmptyConstraint());
        return myself;
    }

    public final MapSubject<K, V> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyConstraint());
        return myself;
    }

    public final MapSubject<K, V> hasSize(int expectedSize) {
        rule.addConstraint(new HasSizeConstraint(expectedSize));
        return myself;
    }

    public final MapSubject<K, V> containsKey(Object key) {

        return myself;
    }

    public final MapSubject<K, V> containsEntry(Object key, Object value) {

        return myself;
    }

    public final MapSubject<K, V> doesNotContainsEntry(Object key, Object value) {

        return myself;
    }

    // Fails if the map is not empty...This seems strange
    public final MapSubject<K, V> containsExactly() {
        return myself;
    }

    public final MapSubject<K, V> containsExactly(Object key, Object value, Object... rest) {
        return myself;
    }

    public final MapSubject<K, V> containsExactlyEntriesIn(Map<?, ?> expectedMap) {

        return myself;
    }

    public final MapSubject<K, V> containsExactlyEntriesInAnyOrder(Map<?, ?> expected) {

        return myself;
    }

    // TODO: review Google Truth MapSubject MapDifference
    public final MapSubject<K, V> doesNotContainEntry(Object key, Object value) {

        return myself;
    }


    public final MapSubject<K, V> containsValue(V value) {
        rule.addConstraint(new ContainsValueConstraint(value));
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
    public MapSubject<K, V> isNull() {
        return super.isNull();
    }

    @Override
    public MapSubject<K, V> isNotNull() {
        return super.isNotNull();
    }

    @Override
    public MapSubject<K, V> isEquals(Map<K, V> other) {
        return super.isEquals(other);
    }

    /**
     *
     * @param constraintsToAdd
     * @return
     */
    public final MapSubject<K, V> forEachEntry(Constraint<?, ? super Map.Entry<K, V>>... constraintsToAdd) {
        // TODO: fix unchecked warning
        rule.addConstraint(new EntryConstraint(constraintsToAdd));
        return myself;
    }

    /**
     *
     * @param predicate
     * @param constraintsToAdd
     * @return
     */
    public final MapSubject<K, V> forEachEntry(Predicate<? super Map.Entry<K, V>> predicate, Constraint<?, ? super Map.Entry<K, V>>... constraintsToAdd) {
        rule.addConstraint(new EntryConstraint(predicate, constraintsToAdd));
        return myself;
    }


    /**
     *
     * @param constraintsToAdd
     * @return
     */
    public final MapSubject<K, V> forEachKey(Constraint<?, ? super K>... constraintsToAdd) {
        // TODO: fix unchecked warning
        rule.addConstraint(new KeyConstraint(constraintsToAdd));
        return myself;
    }

//    /**
//     *
//     * @param predicate
//     * @param constraintsToAdd
//     * @return
//     */
//    public final MapSubject<K, V> forEachKey(Predicate<? super K> predicate, Constraint<?, ? super K>... constraintsToAdd) {
//        rule.addConstraint(new KeyConstraint(constraintsToAdd));
//        return myself;
//    }

    /**
     *
     * @param constraintsToAdd
     * @return
     */
    public final MapSubject<K, V> forEachValue(Constraint<?, ? super V>... constraintsToAdd) {
        // TODO: fix unchecked warning
        rule.addConstraint(new ValueConstraint(constraintsToAdd));
        return myself;
    }

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
}
