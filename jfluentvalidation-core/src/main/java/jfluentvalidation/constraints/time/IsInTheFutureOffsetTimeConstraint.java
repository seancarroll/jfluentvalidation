package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.time.OffsetTime;
import java.util.function.Supplier;

public class IsInTheFutureOffsetTimeConstraint<T> extends IsAfterOffsetTimeConstraint<T> {
    public IsInTheFutureOffsetTimeConstraint(Supplier<OffsetTime> other) {
        super(DefaultMessages.TIME_IS_IN_FUTURE, other);
    }
}
