package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.List;

public class ContainsAnyDoubleConstraint<T> extends AbstractConstraint<T, double[]> {

    private final Iterable<Double> values;

    public ContainsAnyDoubleConstraint(Iterable<Double> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Double> actual = asList(context.getPropertyValue());
        for (double item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

    private static List<Double> asList(double[] doubles) {
        final List<Double> list = new ArrayList<>(doubles.length);
        for (double b : doubles) {
            list.add(b);
        }
        return list;
    }
}
