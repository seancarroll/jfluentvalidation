package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.util.Date;
import java.util.function.Supplier;

public class IsInTheFutureDateConstraint<T> extends IsAfterDateConstraint<T> {
    public IsInTheFutureDateConstraint(Supplier<Date> other) {
        super(DefaultMessages.TIME_IS_IN_FUTURE, other);
    }
}
