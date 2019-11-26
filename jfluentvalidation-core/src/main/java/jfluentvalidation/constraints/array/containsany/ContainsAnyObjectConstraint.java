package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ContainsAnyObjectConstraint<T, E> extends AbstractConstraint<T, E[]> {

    private final Iterable<E> values;

    public ContainsAnyObjectConstraint(Iterable<E> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, E[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        // TODO: perf test compared to adding
        List<E> actual = Arrays.stream(context.getPropertyValue()).collect(Collectors.toList());
        for (E item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }
}
