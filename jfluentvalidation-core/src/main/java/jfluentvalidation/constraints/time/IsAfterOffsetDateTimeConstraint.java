package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.OffsetDateTime;

public class IsAfterOffsetDateTimeConstraint<T> implements Constraint<T, OffsetDateTime> {

    private final OffsetDateTime other;

    public IsAfterOffsetDateTimeConstraint(OffsetDateTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetDateTime> context) {
        return context.getPropertyValue().isAfter(other);
    }
}
