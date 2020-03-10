package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

/**
 *
 * @param <T>
 */
public class IsInTheFutureZonedDateTimeConstraint<T> extends IsAfterZonedDateTimeConstraint<T> {
    public IsInTheFutureZonedDateTimeConstraint(Supplier<ZonedDateTime> other) {
        super(DefaultMessages.TIME_IS_IN_FUTURE, other);
    }
}
