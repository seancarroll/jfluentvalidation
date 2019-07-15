package jfluentvalidation;

import jfluentvalidation.constraints.Constraint;

import java.util.function.Predicate;

public class CollectionItemConstraint<T, E> {

    private Predicate<E> predicate;
    private Constraint<T, E> constraint;

    public CollectionItemConstraint(Constraint<T, E> constraint) {
        this.constraint = constraint;
    }

    public CollectionItemConstraint(Constraint<T, E> constraint, Predicate<E> predicate) {
        this.constraint = constraint;
        this.predicate = predicate;
    }


    public Predicate<E> getPredicate() {
        return predicate;
    }

    public Constraint<T, E> getConstraint() {
        return constraint;
    }
}
