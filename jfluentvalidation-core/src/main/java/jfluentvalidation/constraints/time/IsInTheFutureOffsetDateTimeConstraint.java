package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.time.OffsetDateTime;
import java.util.function.Supplier;

public class IsInTheFutureOffsetDateTimeConstraint<T> extends IsAfterOffsetDateTimeConstraint<T> {
    public IsInTheFutureOffsetDateTimeConstraint(Supplier<OffsetDateTime> other) {
        super(DefaultMessages.TIME_IS_IN_FUTURE, other);
    }
}
