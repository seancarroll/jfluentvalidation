package jfluentvalidation.constraints.array.containsany;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.List;

import static jfluentvalidation.common.Lists.asList;

public class ContainsAnyCharConstraint<T> extends AbstractConstraint<T, char[]> {

    private final Iterable<Character> values;

    public ContainsAnyCharConstraint(Iterable<Character> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (context.getPropertyValue().length == 0 && !values.iterator().hasNext()) {
            return true;
        }

        List<Character> actual = asList(context.getPropertyValue());
        for (char item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addParametersToContext(RuleContext<T, char[]> context) {
        context.getMessageContext().appendArgument("values", values);
    }
}
