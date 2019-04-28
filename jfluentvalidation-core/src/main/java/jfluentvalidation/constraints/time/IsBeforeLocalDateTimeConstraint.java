package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDateTime;

/**
 *
 * @param <T>  the target type supported by an implementation.
 */
public class IsBeforeLocalDateTimeConstraint<T> implements Constraint<T, LocalDateTime> {

    private final LocalDateTime other;

    public IsBeforeLocalDateTimeConstraint(LocalDateTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDateTime> context) {
        return context.getPropertyValue().isBefore(other);
    }
}
