package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.Clock;
import java.time.LocalDate;

public class IsTodayLocalDateConstraint<T> extends AbstractConstraint<T, LocalDate> {

    private final Clock clock;

    public IsTodayLocalDateConstraint(Clock clock) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.clock = Ensure.notNull(clock);
    }

    @Override
    public boolean isValid(RuleContext<T, LocalDate> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().equals(LocalDate.now(clock));
    }
}
