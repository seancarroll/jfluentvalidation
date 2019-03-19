package jfluentvalidation.constraints.time;

import java.time.*;
import java.util.Calendar;

public final class TimeConstraints {

    public static IsAfterCalendarConstraint isAfterCalendar(Calendar other) {
        return new IsAfterCalendarConstraint(other);
    }

    public static IsAfterLocalDateConstraint isAfterLocalDate(LocalDate other) {
        return new IsAfterLocalDateConstraint(other);
    }

    public static IsAfterLocalDateTimeConstraint isAfterLocalDateTime(LocalDateTime other) {
        return new IsAfterLocalDateTimeConstraint(other);
    }

    public static IsAfterLocalTimeConstraint isAfterLocalTime(LocalTime other) {
        return new IsAfterLocalTimeConstraint(other);
    }

    public static IsAfterOffsetDateTimeConstraint isAfterOffsetDateTime(OffsetDateTime other) {
        return new IsAfterOffsetDateTimeConstraint(other);
    }

    public static IsAfterOffsetTimeConstraint isAfterOffsetTime(OffsetTime other) {
        return new IsAfterOffsetTimeConstraint(other);
    }

    public static IsAfterOrEqualCalendarConstraint isAfterOrEqualCalendar(Calendar other) {
        return new IsAfterOrEqualCalendarConstraint(other);
    }

    public static IsAfterOrEqualLocalDateConstraint isAfterOrEqualLocalDate(LocalDate other) {
        return new IsAfterOrEqualLocalDateConstraint(other);
    }

    public static IsAfterOrEqualLocalDateTimeConstraint isAfterOrEqualLocalDateTime(LocalDateTime other) {
        return new IsAfterOrEqualLocalDateTimeConstraint(other);
    }

    public static IsAfterOrEqualLocalTimeConstraint isAfterOrEqualLocalTime(LocalTime other) {
        return new IsAfterOrEqualLocalTimeConstraint(other);
    }

    public static IsAfterOrEqualOffsetDateTimeConstraint isAfterOrEqualOffsetDateTime(OffsetDateTime other) {
        return new IsAfterOrEqualOffsetDateTimeConstraint(other);
    }

    public static IsAfterOrEqualOffsetTimeConstraint isAfterOrEqualOffsetTime(OffsetTime other) {
        return new IsAfterOrEqualOffsetTimeConstraint(other);
    }

    public static IsAfterOrEqualZonedDateTimeConstraint isAfterOrEqualZonedDateTime(ZonedDateTime other) {
        return new IsAfterOrEqualZonedDateTimeConstraint(other);
    }

    public static IsAfterZonedDateTimeConstraint isAfterZonedDateTime(ZonedDateTime other) {
        return new IsAfterZonedDateTimeConstraint(other);
    }

    public static IsBeforeCalendarConstraint isBeforeCalendar(Calendar other) {
        return new IsBeforeCalendarConstraint(other);
    }

    public static IsBeforeLocalDateConstraint isBeforeLocalDate(LocalDate other) {
        return new IsBeforeLocalDateConstraint(other);
    }

    public static IsBeforeLocalDateTimeConstraint isBeforeLocalDate(LocalDateTime other) {
        return new IsBeforeLocalDateTimeConstraint(other);
    }

    public static IsBeforeLocalTimeConstraint isBeforeLocalTime(LocalTime other) {
        return new IsBeforeLocalTimeConstraint(other);
    }

    public static IsBeforeOffsetDateTimeConstraint isBeforeOffsetDateTime(OffsetDateTime other) {
        return new IsBeforeOffsetDateTimeConstraint(other);
    }

    public static IsBeforeOffsetTimeConstraint isBeforeOffsetTime(OffsetTime other) {
        return new IsBeforeOffsetTimeConstraint(other);
    }

    public static IsBeforeOrEqualCalendarConstraint isBeforeOrEqualCalendar(Calendar other) {
        return new IsBeforeOrEqualCalendarConstraint(other);
    }

    public static IsBeforeOrEqualLocalDateConstraint isBeforeOrEqualLocalDate(LocalDate other) {
        return new IsBeforeOrEqualLocalDateConstraint(other);
    }

    public static IsBeforeOrEqualLocalDateTimeConstraint isBeforeOrEqualLocalTime(LocalDateTime other) {
        return new IsBeforeOrEqualLocalDateTimeConstraint(other);
    }

    public static IsBeforeOrEqualLocalTimeConstraint isBeforeOrEqualLocalTime(LocalTime other) {
        return new IsBeforeOrEqualLocalTimeConstraint(other);
    }

    public static IsBeforeOrEqualOffsetDateTimeConstraint isBeforeOrEqualOffsetDateTime(OffsetDateTime other) {
        return new IsBeforeOrEqualOffsetDateTimeConstraint(other);
    }

    public static IsBeforeOrEqualOffsetTimeConstraint isBeforeOrEqualOffsetTime(OffsetTime other) {
        return new IsBeforeOrEqualOffsetTimeConstraint(other);
    }

    public static IsBeforeOrEqualZonedDateTimeConstraint isBeforeOrEqualZonedDateTime(ZonedDateTime other) {
        return new IsBeforeOrEqualZonedDateTimeConstraint(other);
    }

    public static IsBeforeZonedDateTimeConstraint isBeforeZonedDateTime(ZonedDateTime other) {
        return new IsBeforeZonedDateTimeConstraint(other);
    }

    private TimeConstraints() {
        // public static factory methods only
    }
}
