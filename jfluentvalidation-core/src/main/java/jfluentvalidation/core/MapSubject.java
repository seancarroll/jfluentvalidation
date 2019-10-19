package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import jfluentvalidation.MapItemConstraint;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.constraints.map.ContainsEntriesConstraint;
import jfluentvalidation.constraints.map.ContainsKeysConstraint;
import jfluentvalidation.constraints.map.ContainsValuesConstraint;
import jfluentvalidation.constraints.map.DoesNotContainEntriesConstraint;
import jfluentvalidation.constraints.map.DoesNotContainKeysConstraint;
import jfluentvalidation.constraints.map.DoesNotContainValuesConstraint;
import jfluentvalidation.constraints.map.HasSizeConstraint;
import jfluentvalidation.constraints.map.IsEmptyConstraint;
import jfluentvalidation.constraints.map.IsNotEmptyConstraint;
import jfluentvalidation.rules.MapPropertyRule;
import jfluentvalidation.rules.PropertyRule;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import static jfluentvalidation.common.Maps.entry;
import static jfluentvalidation.common.MoreArrays.array;

/**
 *
 * @param <T>  the target type supported by an implementation.
 * @param <K>  the type of keys maintained by the map
 * @param <V>  the type of mapped values
 */
public class MapSubject<T, K, V> extends Subject<MapSubject<T, K, V>, T, Map<K, V>> {

    public MapSubject(PropertyRule<T, Map<K, V>> rule) {
        super(MapSubject.class, rule);
    }

    /**
     * Verifies that the {@link Map} is empty.
     *
     */
    public final void isEmpty() {
        rule.addConstraint(new IsEmptyConstraint<>());
    }

    /**
     * Verifies that the {@link Map} is not empty.
     *
     * @return  {@code this} subject.
     */
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> isNotEmpty() {
        rule.addConstraint(new IsNotEmptyConstraint<>());
        return myself;
    }

    /**
     *
     * @param expectedSize
     * @return  {@code this} subject.
     */
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> hasSize(int expectedSize) {
        rule.addConstraint(new HasSizeConstraint<>(expectedSize));
        return myself;
    }

    /**
     *
     * @param entries
     * @return  {@code this} subject.
     * @throws NullPointerException if entries is {@code null}.
     */
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> contains(@Nonnull Map.Entry<? extends K, ? extends V>... entries) {
        rule.addConstraint(new ContainsEntriesConstraint<>(entries));
        return myself;
    }

    /**
     *
     * @param key
     * @param value
     * @return  {@code this} subject.
     */
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsEntry(K key, V value) {
        rule.addConstraint(new ContainsEntriesConstraint<>(array(entry(key, value))));
        return myself;
    }

    /**
     *
     * @param keys
     * @return  {@code this} subject.
     * @throws NullPointerException if keys is {@code null}.
     */
    @SafeVarargs
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsKeys(@Nonnull K... keys) {
        rule.addConstraint(new ContainsKeysConstraint<>(keys));
        return myself;
    }

    /**
     *
     * @param values
     * @return  {@code this} subject.
     * @throws NullPointerException if values is {@code null}.
     */
    @SafeVarargs
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsValues(@Nonnull V... values) {
        rule.addConstraint(new ContainsValuesConstraint<>(values));
        return myself;
    }

    /**
     *
     * @return  {@code this} subject.
     */
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsExactly() {
        // TODO: implement
        return myself;
    }

    // TODO: method args suck...remove or improve
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsExactly(Object key, Object value, Object... rest) {
        // TODO: implement
        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsExactlyEntriesIn(Map<?, ?> expectedMap) {
    // TODO: implement
        return myself;
    }

    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> containsExactlyEntriesInAnyOrder(Map<?, ?> expected) {
        // TODO: implement
        return myself;
    }

    /**
     *
     * @param entries
     * @return  {@code this} subject.
     * @throws NullPointerException if entries is {@code null}.
     */
    @SafeVarargs
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> doesNotContain(@Nonnull Map.Entry<? extends K, ? extends V>... entries) {
        rule.addConstraint(new DoesNotContainEntriesConstraint<>(entries));
        return myself;
    }

    /**
     *
     * @param key
     * @param value
     * @return  {@code this} subject.
     */
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> doesNotContainEntry(K key, V value) {
        rule.addConstraint(new DoesNotContainEntriesConstraint<>(array(entry(key, value))));
        return myself;
    }

    /**
     *
     * @param keys
     * @return  {@code this} subject.
     * @throws NullPointerException if keys is {@code null}.
     */
    @SafeVarargs
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> doesNotContainKeys(@Nonnull K... keys) {
        rule.addConstraint(new DoesNotContainKeysConstraint<>(keys));
        return myself;
    }

    /**
     *
     * @param values
     * @return  {@code this} subject.
     * @throws NullPointerException if values is {@code null}.
     */
    @SafeVarargs
    @CanIgnoreReturnValue
    public final MapSubject<T, K, V> doesNotContainValues(@Nonnull V... values) {
        rule.addConstraint(new DoesNotContainValuesConstraint<>(values));
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
    // hasEntrySatisfying(K key, Condition<? super V> valueCondition)
    // hasEntrySatisfying(K key, Consumer<? super V> valueRequirements)
    // hasEntrySatisfying(Condition<? super Map.Entry<K, V>> entryCondition)
    // hasEntrySatisfying(Condition<? super K> keyCondition, Condition<? super V> valueCondition)
    // hasKeySatisfying(Condition<? super K> keyCondition)
    // hasValueSatisfying(Condition<? super V> valueCondition)


    // TODO: I'm not sure this makes sense given what would a Constraint for an Entry look like?
    // Perhaps we do soemthing like assertj's satisfy/satisfyrequirements
    /**
     *
     * @param constraintsToAdd
     * @return  {@code this} subject.
     */
    @CanIgnoreReturnValue
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
     * @return  {@code this} subject.
     */
    @CanIgnoreReturnValue
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
     * @return  {@code this} subject.
     */
    @CanIgnoreReturnValue
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
        return (MapPropertyRule<T, K, V>) super.getRule();
    }
}
