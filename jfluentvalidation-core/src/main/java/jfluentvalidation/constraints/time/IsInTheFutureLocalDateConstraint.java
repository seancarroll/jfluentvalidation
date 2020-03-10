package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.time.LocalDate;
import java.util.function.Supplier;

public class IsInTheFutureLocalDateConstraint<T> extends IsAfterLocalDateConstraint<T> {
    public IsInTheFutureLocalDateConstraint(Supplier<LocalDate> other) {
        super(DefaultMessages.TIME_IS_IN_FUTURE, other);
    }
}
