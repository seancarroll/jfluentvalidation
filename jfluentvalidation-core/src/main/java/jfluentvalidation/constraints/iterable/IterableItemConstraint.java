package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class IterableItemConstraint<P> implements Constraint<Iterable<? super P>> {

    List<Constraint<? super P>> constraints;
    Predicate<? super P> predicate;

    public IterableItemConstraint(Constraint<? super P>... constraints) {
        this(null, constraints);
    }

    public IterableItemConstraint(Predicate<? super P> predicate, Constraint<? super P>... constraints) {
        this.constraints = Arrays.asList(constraints);
        this.predicate = predicate;
    }

    @Override
    public boolean isValid(Iterable<? super P> value) {
        return false;
    }
}
