package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDate;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterLocalDateConstraint<T> extends AbstractConstraint<T, LocalDate> {

    private final Supplier<LocalDate> other;

    public IsAfterLocalDateConstraint(LocalDate other) {
        this(Suppliers.create(other));
    }

    public IsAfterLocalDateConstraint(Supplier<LocalDate> other) {
        super(DefaultMessages.TIME_IS_AFTER);
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDate> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().isAfter(other.get());
    }

    @Override
    public void addParametersToContext(RuleContext<T, LocalDate> context) {
        context.getMessageContext().appendArgument("other", other.get());
    }
}
