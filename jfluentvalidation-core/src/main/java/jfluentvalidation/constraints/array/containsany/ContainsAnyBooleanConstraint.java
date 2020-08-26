package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.List;

import static jfluentvalidation.common.Lists.asList;

public class ContainsAnyBooleanConstraint<T> extends AbstractConstraint<T, boolean[]> {

    private final Iterable<Boolean> values;

    public ContainsAnyBooleanConstraint(Iterable<Boolean> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(ConstraintContext<T, boolean[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Boolean> actual = asList(context.getPropertyValue());
        for (boolean item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, boolean[]> context) {
        context.getMessageContext().appendArgument("values", values);
    }
}
