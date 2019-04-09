package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

// AKA containsAllOf
/**
 * TODO: from Google Truth...we want something similar ot this behavior
 * Checks that the actual iterable contains at least all of the expected elements or fails. If an
 * element appears more than once in the expected elements to this call then it must appear at
 * least that number of times in the actual elements.
 *
 * <p>To also test that the contents appear in the given order, make a call to {@code inOrder()}
 * on the object returned by this method. The expected elements must appear in the given order
 * within the actual elements, but they are not required to be consecutive.
 */
public class ContainsAllInConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    private final Iterable<? extends P> expectedIterable;

    public ContainsAllInConstraint(Iterable<? extends P> expectedIterable) {
        this.expectedIterable = expectedIterable;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return false;
    }

}
