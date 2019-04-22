package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class IterableItemConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    List<Constraint<T, ? super P>> constraints;
    Predicate<? super P> predicate;

    public IterableItemConstraint(Constraint<T, ? super P>... constraints) {
        this(null, constraints);
    }

    public IterableItemConstraint(Predicate<? super P> predicate, Constraint<T, ? super P>... constraints) {
        this.constraints = Arrays.asList(constraints);
        this.predicate = predicate;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return false;
    }
}
