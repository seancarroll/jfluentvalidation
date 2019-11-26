package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.List;

public class ContainsAnyShortConstraint<T> extends AbstractConstraint<T, short[]> {

    private final Iterable<Short> values;

    public ContainsAnyShortConstraint(Iterable<Short> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Short> actual = asList(context.getPropertyValue());
        for (short item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

    private static List<Short> asList(short[] shorts) {
        final List<Short> list = new ArrayList<>(shorts.length);
        for (short b : shorts) {
            list.add(b);
        }
        return list;
    }
}
