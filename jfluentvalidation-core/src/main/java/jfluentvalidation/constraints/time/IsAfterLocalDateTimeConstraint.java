package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDateTime;

public class IsAfterLocalDateTimeConstraint<T> implements Constraint<T, LocalDateTime> {

    private final LocalDateTime other;

    public IsAfterLocalDateTimeConstraint(LocalDateTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDateTime> context) {
        return context.getPropertyValue().isAfter(other);
    }
}
