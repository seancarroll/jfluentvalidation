package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

public class ContainsAnyInConstraint implements Constraint<Iterable<?>> {

    private final Iterable<?> expected;

    public ContainsAnyInConstraint(Iterable<?> expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {
        // TODO: complete
        // Google Truth has SubjectUtils iterableToCollection
//        Collection<?> actual = iterableToCollection(actual());
//        for (Object item : expected) {
//            if (actual.contains(item)) {
//                return true;
//            }
//        }
        return false;
    }
}
