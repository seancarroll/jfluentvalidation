package jfluentvalidation.constraints.array.containsall;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

public class ContainsAllIntConstraint<T> extends AbstractConstraint<T, int[]> {

    private final Iterable<Integer> values;

    public ContainsAllIntConstraint(Iterable<Integer> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ALL);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(ConstraintContext<T, int[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Integer[] valuesAsArray = Iterables.toArray(values, Integer.class);
        Set<Object> notFound = stream(valuesAsArray)
            .filter(value -> !MoreArrays.contains(context.getPropertyValue(), value))
            .collect(toCollection(LinkedHashSet::new));

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingValues", notFound);
        }

        return notFound.isEmpty();
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, int[]> context) {
        context.getMessageContext().appendArgument("expected", values);
    }
}
