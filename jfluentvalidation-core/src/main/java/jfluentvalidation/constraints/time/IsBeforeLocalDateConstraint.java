package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.LocalDate;

public class IsBeforeLocalDateConstraint<T> implements Constraint<T, LocalDate> {

    private final LocalDate other;

    public IsBeforeLocalDateConstraint(LocalDate other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDate> context) {
        return context.getPropertyValue().isBefore(other);
    }
}
