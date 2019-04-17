package jfluentvalidation.core;


import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.function.Predicate;

// TODO: should we delete
//extends Subject<CollectionForEachSubject<A>, Iterable<? super A>
public abstract class CollectionForEachSubject<S extends CollectionForEachSubject<S, A>, A>
    extends Subject<S, A> {

    public CollectionForEachSubject(Class<?> selfType, PropertyRule<S, A> rule) {
        super(selfType, rule);
    }

    S where(Predicate<A> predicate) {
        return myself;
    }

    S ruleForEach(Constraint<?, A> constraint) {
        // TODO: fix
        // rule.addConstraint(constraint);
        // constraints.add(constraint);
        return myself;
    }
}
