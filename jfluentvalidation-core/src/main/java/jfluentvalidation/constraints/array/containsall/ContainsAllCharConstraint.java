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

public class ContainsAllCharConstraint<T> extends AbstractConstraint<T, char[]> {

    private final Iterable<Character> values;

    public ContainsAllCharConstraint(Iterable<Character> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ALL_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Character[] valuesAsArray = Iterables.toArray(values, Character.class);
        Set<Object> notFound = stream(valuesAsArray)
            .filter(value -> !MoreArrays.contains(context.getPropertyValue(), value))
            .collect(toCollection(LinkedHashSet::new));

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingValues", notFound);
        }

        return notFound.isEmpty();
    }

}
