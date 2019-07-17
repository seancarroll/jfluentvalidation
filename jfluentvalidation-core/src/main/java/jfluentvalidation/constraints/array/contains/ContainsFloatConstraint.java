package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ContainsFloatConstraint<T> extends AbstractConstraint<T, float[]> {

    private final float element;

    public ContainsFloatConstraint(float element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        return MoreArrays.contains(context.getPropertyValue(), element);
    }
}
