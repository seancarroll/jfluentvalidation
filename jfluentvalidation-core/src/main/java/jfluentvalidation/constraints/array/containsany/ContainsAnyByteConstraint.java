package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.List;

import static jfluentvalidation.common.Lists.asList;

public class ContainsAnyByteConstraint<T> extends AbstractConstraint<T, byte[]> {

    private final Iterable<Byte> values;

    public ContainsAnyByteConstraint(Iterable<Byte> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Byte> actual = asList(context.getPropertyValue());
        for (Byte item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addParametersToContext(RuleContext<T, byte[]> context) {
        context.getMessageContext().appendArgument("values", values);
    }
}
