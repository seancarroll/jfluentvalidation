package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * TODO: From Google Truth...we want similar behavior
 * Checks that the subject contains no elements that correspond to any of the given elements.
 * (Duplicates are irrelevant to this test, which fails if any of the subject elements
 * correspond to any of the given elements.)
 */
public class ContainsNoneInConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    private final Iterable<? extends P> excluded;

    public ContainsNoneInConstraint(Iterable<? extends P> excluded) {
        this.excluded = excluded;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {

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
