package jfluentvalidation.constraints.array.containsexactly;

import jfluentvalidation.common.IterableDifference;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.List;
import java.util.Objects;

import static jfluentvalidation.common.Lists.toList;

// TODO: have a base class for array, iterable, map???
public class ContainsExactlyConstraint<T, A> extends AbstractConstraint<T, A> {

    private final List<?> expected;

    public ContainsExactlyConstraint(List<?> expected) {
        // TODO: fix message name
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY);
        this.expected =  Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        List actualAsList = toList(context.getPropertyValue());
        IterableDifference<?> diff = IterableDifference.diff(actualAsList, expected);
        if (!diff.differencesFound()) {
            // actual and values have the same elements but are they in the same order ?
            for (int i = 0; i < actualAsList.size(); i++) {
                Object actualElement = actualAsList.get(i);
                Object expectedElement = expected.get(i);
                if (!Objects.equals(actualElement, expectedElement)) {
                    context.getMessageContext().appendArgument("differentOrderElement", actualElement);
                    context.getMessageContext().appendArgument("indexOfDifferentElement", i);
                    context.getMessageContext().appendArgument("missingValues", null);
                    context.getMessageContext().appendArgument("unexpectedValues", null);
                    return false;
                }
            }
            return true;
        }

        // need to set all values to avoid UnresolvablePropertyException when evaluating validation message
        context.getMessageContext().appendArgument("differentOrderElement", null);
        context.getMessageContext().appendArgument("indexOfDifferentElement", null);
        context.getMessageContext().appendArgument("missingValues", diff.getMissing().isEmpty() ? null : diff.getMissing());
        context.getMessageContext().appendArgument("unexpectedValues", diff.getUnexpected().isEmpty() ? null : diff.getUnexpected());

        return false;
    }

}
