package jfluentvalidation.common;

import jfluentvalidation.internal.Ensure;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 */
public final class Dates {

    private Dates() {
        // public statics only
    }

    public static Calendar truncateTime(Calendar date) {
        // TODO: throw?
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.getTime());
        // TODO: do I need to explicitly set these?
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * Returns a copy of the given date without the time part (which is set to 00:00:00), for example :<br>
     * <code>truncateTime(2008-12-29T23:45:12)</code> will give <code>2008-12-29T00:00:00</code>.
     * <p>
     * Returns null if the given Date is null.
     *
     * @param date we want to get the day part (the parameter is read only).
     * @return the truncated date.
     */
    public static Date truncateTime(Date date) {
        // TODO: throw?
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // TODO: do I need to explicitly set these?
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static LocalDateTime truncateTime(LocalDateTime date) {
        // TODO: how to handle null?
        return date.truncatedTo(ChronoUnit.DAYS);
    }

    public static OffsetDateTime truncateTime(OffsetDateTime date) {
        // TODO: how to handle null?
        return date.truncatedTo(ChronoUnit.DAYS);
    }

    public static ZonedDateTime truncateTime(ZonedDateTime date) {
        // TODO: how to handle null?
        return date.truncatedTo(ChronoUnit.DAYS);
    }

    public static boolean isSameDay(Calendar date1, Calendar date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        return date1.get(Calendar.ERA) == date2.get(Calendar.ERA) &&
            date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
            date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR);
        // return truncateTime(date1).equals(truncateTime(date2));
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        // return isSameDay(truncateTime(date1), truncateTime(date2));
        return truncateTime(date1).equals(truncateTime(date2));
    }

    public static boolean isSameDay(LocalDateTime date1, LocalDateTime date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        return truncateTime(date1).equals(truncateTime(date2));
    }

    public static boolean isSameDay(OffsetDateTime date1, OffsetDateTime date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        return truncateTime(date1).equals(truncateTime(date2));
    }

    public static boolean isSameDay(ZonedDateTime date1, ZonedDateTime date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        return truncateTime(date1).equals(truncateTime(date2));
    }

    public static Calendar calendarFromClock(Clock clock) {
        return GregorianCalendar.from(ZonedDateTime.now(clock));
    }
}
