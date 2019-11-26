package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.List;

public class ContainsAnyFloatConstraint<T> extends AbstractConstraint<T, float[]> {

    private final Iterable<Float> values;

    public ContainsAnyFloatConstraint(Iterable<Float> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Float> actual = asList(context.getPropertyValue());
        for (float item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

    private static List<Float> asList(float[] floats) {
        final List<Float> list = new ArrayList<>(floats.length);
        for (float b : floats) {
            list.add(b);
        }
        return list;
    }
}
