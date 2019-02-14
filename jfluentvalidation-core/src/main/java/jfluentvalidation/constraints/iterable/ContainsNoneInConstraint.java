package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

public class ContainsNoneInConstraint implements Constraint<Iterable<?>> {

    private final Iterable<?> excluded;

    public ContainsNoneInConstraint(Iterable<?> excluded) {
        this.excluded = excluded;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {

//        Collection<?> actual = iterableToCollection(actual());
//        Collection<Object> present = new ArrayList<>();
//        for (Object item : Sets.newLinkedHashSet(excluded)) {
//            if (actual.contains(item)) {
//                present.add(item);
//            }
//        }

        return false;
    }
}
