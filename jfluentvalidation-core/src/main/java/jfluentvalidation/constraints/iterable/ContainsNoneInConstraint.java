package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * TODO: From Google Truth...we want similar behavior
 * Checks that the subject contains no elements that correspond to any of the given elements.
 * (Duplicates are irrelevant to this test, which fails if any of the subject elements
 * correspond to any of the given elements.)
 */
public class ContainsNoneInConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Iterable<? super P> excluded;

    public ContainsNoneInConstraint(Iterable<? super P> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE_IN);
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
