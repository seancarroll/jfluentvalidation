package jfluentvalidation.core;


import jfluentvalidation.constraints.Constraint;

import java.util.function.Function;
import java.util.function.Predicate;

//extends Subject<CollectionForEachSubject<A>, Iterable<? super A>
public abstract class CollectionForEachSubject<S extends CollectionForEachSubject<S, A>, A>
    extends Subject<S, A> {

    public CollectionForEachSubject(Class<?> selfType, Function propertyFunc, String propertyName) {
        super(CollectionForEachSubject.class, propertyFunc, propertyName);
    }

    S where(Predicate<A> predicate) {
        return myself;
    }

    S ruleForEach(Constraint<A> constraint) {
        constraints.add(constraint);
        return myself;
    }
}
