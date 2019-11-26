package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.List;

public class ContainsAnyIntConstraint<T> extends AbstractConstraint<T, int[]> {

    private final Iterable<Integer> values;

    public ContainsAnyIntConstraint(Iterable<Integer> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Integer> actual = asList(context.getPropertyValue());
        for (int item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

    private static List<Integer> asList(int[] ints) {
        final List<Integer> list = new ArrayList<>(ints.length);
        for (int b : ints) {
            list.add(b);
        }
        return list;
    }
}
