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

public class ContainsAllLongConstraint<T> extends AbstractConstraint<T, long[]> {

    private final Iterable<Long> values;

    public ContainsAllLongConstraint(Iterable<Long> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ALL);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(ConstraintContext<T, long[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Long[] valuesAsArray = Iterables.toArray(values, Long.class);
        Set<Object> notFound = stream(valuesAsArray)
            .filter(value -> !MoreArrays.contains(context.getPropertyValue(), value))
            .collect(toCollection(LinkedHashSet::new));

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingValues", notFound);
        }

        return notFound.isEmpty();
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, long[]> context) {
        context.getMessageContext().appendArgument("expected", values);
    }
}
