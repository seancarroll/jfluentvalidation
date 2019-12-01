package jfluentvalidation.constraints.array.containsall;

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

public class ContainsAllFloatConstraint<T> extends AbstractConstraint<T, float[]> {

    private final Iterable<Float> values;

    public ContainsAllFloatConstraint(Iterable<Float> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ALL_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Float[] valuesAsArray = Iterables.toArray(values, Float.class);
        Set<Object> notFound = stream(valuesAsArray)
            .filter(value -> !MoreArrays.contains(context.getPropertyValue(), value))
            .collect(toCollection(LinkedHashSet::new));

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingValues", notFound);
        }

        return notFound.isEmpty();
    }

}
