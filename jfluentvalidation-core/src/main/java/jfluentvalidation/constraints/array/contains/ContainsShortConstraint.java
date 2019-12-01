package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ContainsShortConstraint<T> extends AbstractConstraint<T, short[]> {

    private final short element;

    public ContainsShortConstraint(short element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return MoreArrays.contains(context.getPropertyValue(), element);
    }

    @Override
    public void addParametersToContext(RuleContext<T, short[]> context) {
        context.getMessageContext().appendArgument("element", element);
    }
}
