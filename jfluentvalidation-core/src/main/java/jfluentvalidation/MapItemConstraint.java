package jfluentvalidation;

import jfluentvalidation.constraints.Constraint;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class MapItemConstraint<T, E> {

    private Function<Map<?, ?>, Collection<E>> f;
    private Predicate<E> predicate;
    private Constraint<T, E> constraint;

    public MapItemConstraint(Function<Map<?, ?>, Collection<E>> f, Constraint<T, E> constraint) {
        this.f = f;
        this.constraint = constraint;
    }

    public MapItemConstraint(Function<Map<?, ?>, Collection<E>> f, Constraint<T, E> constraint, Predicate<E> predicate) {
        this.f = f;
        this.constraint = constraint;
        this.predicate = predicate;
    }

    public Collection<E> getCollection(Map<?, ?> m) {
        return f.apply(m);
    }

    public Function<Map<?, ?>, Collection<E>> getF() {
        return f;
    }

    public Predicate<E> getPredicate() {
        return predicate;
    }

    public Constraint<T, E> getConstraint() {
        return constraint;
    }
}
