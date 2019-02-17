package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

/**
 * TODO: From Google Truth...we want similar behavior
 * Checks that the subject contains no elements that correspond to any of the given elements.
 * (Duplicates are irrelevant to this test, which fails if any of the subject elements
 * correspond to any of the given elements.)
 */
public class ContainsNoneInConstraint implements Constraint<Iterable<?>> {

    private final Iterable<?> excluded;

    public ContainsNoneInConstraint(Iterable<?> excluded) {
        this.excluded = excluded;
    }

    @Override
    public boolean isValid(Iterable<?> value) {

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
