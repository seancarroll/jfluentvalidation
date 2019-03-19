package jfluentvalidation.constraints;

import java.util.Map;
import java.util.function.Predicate;

// TODO: will this work for Map? Do we need a separate one for map?
// Does this cover arrays?
// Should we separate / have multiple of these? One for array, one for iterable and one for map?
public class CollectionConstraint<T> implements Constraint<T> {

    private final Predicate<? super T> condition;
    private final Constraint<? super T>[] innerConstraints;

    public CollectionConstraint(Constraint<? super T>... innerConstraints) {
        this(null, innerConstraints);
    }

    public CollectionConstraint(Predicate<? super T> condition, Constraint<? super T>... innerConstraints) {
        this.condition = condition;
        this.innerConstraints = innerConstraints;
    }

    @Override
    public boolean isValid(T value) {

        // hmmm...map is harder because we arent sure if its a predicate for key or value?
        if (value instanceof Map) {
//            Map map = (Map) value;
//            for (Object entry : map.entrySet()) {
//                if (condition == null || condition.test(entry)) {
//
//                }
//            }
        } else if (value instanceof Iterable) {
            // T is likely not the generic type of the item...hmmmm
            Iterable<T> iterable = (Iterable) value;

            for (T element : iterable) {
                if (condition == null || condition.test(element)) {
                    for (Constraint<? super T> constraint : innerConstraints) {
                        // TODO: do something
                        boolean isValid = constraint.isValid(element);
                    }
                }
            }
        }


        return false;
    }
}
