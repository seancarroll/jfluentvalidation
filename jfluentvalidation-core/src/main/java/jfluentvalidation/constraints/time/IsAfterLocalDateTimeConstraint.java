package jfluentvalidation.constraints.time;

import jfluentvalidation.common.Suppliers;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsAfterLocalDateTimeConstraint<T> extends AbstractConstraint<T, LocalDateTime> {

    private final Supplier<LocalDateTime> other;

    public IsAfterLocalDateTimeConstraint(LocalDateTime other) {
        this(Suppliers.create(other));
    }

    public IsAfterLocalDateTimeConstraint(Supplier<LocalDateTime> other) {
        super(DefaultMessages.TIME_IS_AFTER);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        LocalDateTime otherValue = other.get();
        boolean isAfter = context.getPropertyValue().isAfter(otherValue);
        if (!isAfter) {
            // doing this here instead of using addParametersToContext because there are instances were we are
            // getting a supplier for the current date/time and if we do it in addParametersToContext we will
            // get a slightly different values than what we used for the comparison
            context.getMessageContext().appendArgument("other", otherValue);
        }

        return isAfter;
    }
}
