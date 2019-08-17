package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.Clock;
import java.time.OffsetDateTime;

import static jfluentvalidation.common.Dates.isSameDay;

public class IsTodayOffsetDateTimeConstraint<T> extends AbstractConstraint<T, OffsetDateTime> {

    private final Clock clock;

    public IsTodayOffsetDateTimeConstraint(Clock clock) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.clock = Ensure.notNull(clock);
    }

    @Override
    public boolean isValid(RuleContext<T, OffsetDateTime> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return isSameDay(context.getPropertyValue(), OffsetDateTime.now(clock));
    }
}
