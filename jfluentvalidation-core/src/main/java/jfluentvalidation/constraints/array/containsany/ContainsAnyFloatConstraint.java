package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.List;

import static jfluentvalidation.common.Lists.asList;

public class ContainsAnyFloatConstraint<T> extends AbstractConstraint<T, float[]> {

    private final Iterable<Float> values;

    public ContainsAnyFloatConstraint(Iterable<Float> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY);
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

    @Override
    public void addParametersToContext(RuleContext<T, float[]> context) {
        context.getMessageContext().appendArgument("values", values);
    }
}
