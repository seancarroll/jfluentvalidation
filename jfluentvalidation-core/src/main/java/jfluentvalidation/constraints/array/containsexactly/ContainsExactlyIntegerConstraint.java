package jfluentvalidation.constraints.array.containsexactly;

import com.google.common.primitives.Ints;
import jfluentvalidation.common.IterableDifference;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ContainsExactlyIntegerConstraint <T> extends AbstractConstraint<T, int[]> {

    private final Integer[] expected;

    public ContainsExactlyIntegerConstraint(Integer[] expected) {
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY_ELEMENTS_IN);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        List<Integer> actualAsList = Ints.asList(context.getPropertyValue());
        IterableDifference<Integer> diff = IterableDifference.diff(Arrays.asList(expected), actualAsList);
        if (!diff.differencesFound()) {
            // actual and values have the same elements but are they in the same order ?
            // int arrayLength = MoreArrays.len(actual);
            for (int i = 0; i < actualAsList.size(); i++) {
                // Object actualElement = Array.get(actual, i);
                Object actualElement = actualAsList.get(i);
                // Object expectedElement = Array.get(values, i);
                Object expectedElement = Array.get(expected, i);
                // if (!areEqual(actualElement, expectedElement))
                //    throw failures.failure(info, elementsDifferAtIndex(actualElement, expectedElement, i, comparisonStrategy));
                if (!Objects.equals(actualElement, expectedElement)) {
                    context.getMessageContext().appendArgument("differentOrder", actualElement);
                    return false;
                }
            }
            return true;
        }

        if (diff.hasMissing()) {
            context.getMessageContext().appendArgument("missingValues", diff.getMissing());
        }

        if (diff.hasUnexpected()) {
            context.getMessageContext().appendArgument("unexpectedValues", diff.getUnexpected());
        }

        return false;
    }
}
