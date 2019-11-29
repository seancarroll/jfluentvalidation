package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.List;

import static jfluentvalidation.common.Lists.asList;

public class ContainsAnyLongConstraint<T> extends AbstractConstraint<T, long[]> {

    private final Iterable<Long> values;

    public ContainsAnyLongConstraint(Iterable<Long> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY_IN);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Long> actual = asList(context.getPropertyValue());
        for (long item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

}
