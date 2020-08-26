package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.List;

import static jfluentvalidation.common.Lists.asList;

public class ContainsAnyIntConstraint<T> extends AbstractConstraint<T, int[]> {

    private final Iterable<Integer> values;

    public ContainsAnyIntConstraint(Iterable<Integer> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(ConstraintContext<T, int[]> context) {
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

    @Override
    public void addParametersToContext(ConstraintContext<T, int[]> context) {
        context.getMessageContext().appendArgument("values", values);
    }
}
