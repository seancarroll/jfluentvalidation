package jfluentvalidation.constraints.array.containsallof;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

public class ContainsAllOfByteConstraint<T> extends AbstractConstraint<T, byte[]> {

    private final Iterable<Byte> values;

    public ContainsAllOfByteConstraint(Iterable<Byte> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ALL_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
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

}
