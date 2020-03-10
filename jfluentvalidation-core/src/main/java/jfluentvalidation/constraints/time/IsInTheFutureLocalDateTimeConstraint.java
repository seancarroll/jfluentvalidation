package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class IsInTheFutureLocalDateTimeConstraint<T> extends IsAfterLocalDateTimeConstraint<T> {
    public IsInTheFutureLocalDateTimeConstraint(Supplier<LocalDateTime> other) {
        super(DefaultMessages.TIME_IS_IN_FUTURE, other);
    }
}
