package jfluentvalidation.constraints.numbers;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Objects;

import static jfluentvalidation.common.Comparables.isGreaterThan;
import static jfluentvalidation.common.Comparables.isGreaterThanOrEqual;

public abstract class AbstractIsNotCloseToConstraint<T, P extends Number & Comparable<P>> extends AbstractConstraint<T, P> {

    private final P other;
    private final P offset;
    private final boolean strict;

    public AbstractIsNotCloseToConstraint(P other, P offset) {
        this(other, offset, false);
    }

    public AbstractIsNotCloseToConstraint(P other, P offset, boolean strict) {
        super(DefaultMessages.IS_CLOSE_TO);
        this.other = Ensure.notNull(other);
        this.offset = Ensure.notNull(offset);
        this.strict = strict;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        P value = context.getPropertyValue();
        if (Objects.equals(value, other)) {
            return false;
        }

        P absDiff = absDiff(value, other);
        if (strict) {
            return isGreaterThanOrEqual(absDiff, offset);
        }

        return isGreaterThan(absDiff, offset);
    }

    @Override
    public void addParametersToContext(RuleContext<T, P> context) {
        context.getMessageContext().appendArgument("other", other);
        context.getMessageContext().appendArgument("offset", offset);
        context.getMessageContext().appendArgument("strict", strict);
    }

    protected abstract P absDiff(final P actual, final P other);

}
