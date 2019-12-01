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

public class ContainsAllDoubleConstraint<T> extends AbstractConstraint<T, double[]> {

    private final Iterable<Double> values;

    public ContainsAllDoubleConstraint(Iterable<Double> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ALL);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Double[] valuesAsArray = Iterables.toArray(values, Double.class);
        Set<Object> notFound = stream(valuesAsArray)
            .filter(value -> !MoreArrays.contains(context.getPropertyValue(), value))
            .collect(toCollection(LinkedHashSet::new));

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingValues", notFound);
        }

        return notFound.isEmpty();
    }

    @Override
    public void addParametersToContext(RuleContext<T, double[]> context) {
        context.getMessageContext().appendArgument("expected", values);
    }
}
