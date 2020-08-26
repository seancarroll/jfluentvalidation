package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.List;

import static jfluentvalidation.common.Lists.asList;

public class ContainsAnyShortConstraint<T> extends AbstractConstraint<T, short[]> {

    private final Iterable<Short> values;

    public ContainsAnyShortConstraint(Iterable<Short> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(ConstraintContext<T, short[]> context) {
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

    @Override
    public void addParametersToContext(ConstraintContext<T, short[]> context) {
        context.getMessageContext().appendArgument("values", values);
    }
}
