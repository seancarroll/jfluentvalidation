package jfluentvalidation;

import jfluentvalidation.constraints.Constraint;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class MapItemConstraint<T, K, V, E> {

    private Function<Map<K, V>, Collection<E>> f;
    private Predicate<E> predicate;
    private Constraint<T, E> constraint;

    public MapItemConstraint(Function<Map<K, V>, Collection<E>> f, Constraint<T, E> constraint) {
        this.f = f;
        this.constraint = constraint;
    }

    public MapItemConstraint(Function<Map<K, V>, Collection<E>> f, Constraint<T, E> constraint, Predicate<E> predicate) {
        this.f = f;
        this.constraint = constraint;
        this.predicate = predicate;
    }

    public Collection<E> getCollection(Map<K, V> m) {
        return f.apply(m);
    }

    public Function<Map<K, V>, Collection<E>> getF() {
        return f;
    }

    public Predicate<E> getPredicate() {
        return predicate;
    }

    public Constraint<T, E> getConstraint() {
        return constraint;
    }
}
