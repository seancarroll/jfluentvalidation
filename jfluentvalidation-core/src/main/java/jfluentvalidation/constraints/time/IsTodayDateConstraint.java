package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.time.Clock;
import java.util.Date;

import static jfluentvalidation.common.Dates.isSameDay;

public class IsTodayDateConstraint<T> extends AbstractConstraint<T, Date> {

    private final Clock clock;

    public IsTodayDateConstraint(Clock clock) {
        super(DefaultMessages.TIME_IS_BEFORE);
        this.clock = Ensure.notNull(clock);
    }

    @Override
    public boolean isValid(RuleContext<T, Date> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return isSameDay(context.getPropertyValue(), Date.from(clock.instant()));
    }
}
