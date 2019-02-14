package jfluentvalidation.constraints.iterable;

import com.google.common.collect.Iterators;
import jfluentvalidation.constraints.Constraint;

import java.util.Collection;

public class ContainsConstraint implements Constraint<Iterable<?>> {

    private final Object element;

    public ContainsConstraint(Object element) {
        this.element = element;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {
        // TODO: move out to something common to share with doesNotContain
        if (instance instanceof Collection) {
            Collection<?> collection = (Collection)instance;
            // TODO: should have a safeConstains like guava that checks for ClassCastException and potentially NullPointerException?
            return collection.contains(element);
        } else {
            return Iterators.contains(instance.iterator(), element);
        }
    }
}
