package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.time.LocalTime;
import java.util.function.Supplier;

public class IsInTheFutureLocalTimeConstraint<T> extends IsAfterLocalTimeConstraint<T> {
    public IsInTheFutureLocalTimeConstraint(Supplier<LocalTime> other) {
        super(DefaultMessages.TIME_IS_IN_FUTURE, other);
    }
}
