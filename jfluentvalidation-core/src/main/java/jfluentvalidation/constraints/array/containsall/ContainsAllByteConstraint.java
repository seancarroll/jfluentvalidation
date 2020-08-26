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

public class ContainsAllByteConstraint<T> extends AbstractConstraint<T, byte[]> {

    private final Iterable<Byte> values;

    public ContainsAllByteConstraint(Iterable<Byte> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ALL);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(ConstraintContext<T, byte[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Byte[] valuesAsArray = Iterables.toArray(values, Byte.class);
        Set<Object> notFound = stream(valuesAsArray)
            .filter(value -> !MoreArrays.contains(context.getPropertyValue(), value))
            .collect(toCollection(LinkedHashSet::new));

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingValues", notFound);
        }

        return notFound.isEmpty();
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, byte[]> context) {
        context.getMessageContext().appendArgument("expected", values);
    }
}
