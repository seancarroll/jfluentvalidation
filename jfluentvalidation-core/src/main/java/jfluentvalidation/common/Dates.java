package jfluentvalidation.common;

import jfluentvalidation.internal.Ensure;

import javax.annotation.Nonnull;
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

    /**
     *
     * @param date
     * @return
     */
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

    /**
     * Truncates a time from the date.
     *
     * @param date  the date, not null
     * @return  the truncated date, not null
     */
    public static LocalDateTime truncateTime(@Nonnull LocalDateTime date) {
        // TODO: how to handle null?
        return date.truncatedTo(ChronoUnit.DAYS);
    }

    /**
     * Truncates a time from the date.
     *
     * @param date  the date, not null
     * @return  the truncated date, not null
     */
    public static OffsetDateTime truncateTime(@Nonnull OffsetDateTime date) {
        // TODO: how to handle null?
        return date.truncatedTo(ChronoUnit.DAYS);
    }

    /**
     * Truncates a time from the date.
     *
     * @param date  the date, not null
     * @return  the truncated date, not null
     */
    public static ZonedDateTime truncateTime(@Nonnull ZonedDateTime date) {
        // TODO: how to handle null?
        return date.truncatedTo(ChronoUnit.DAYS);
    }

    /**
     * Checks if two date objects are on the same day ignoring time.
     *
     * @param date1  the first date, not null
     * @param date2  the second date, not null
     * @return  true if they represent the same day
     * @throws NullPointerException if date1 or date2 is {@code null}.
     */
    public static boolean isSameDay(@Nonnull Calendar date1, @Nonnull Calendar date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        return date1.get(Calendar.ERA) == date2.get(Calendar.ERA) &&
            date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) &&
            date1.get(Calendar.DAY_OF_YEAR) == date2.get(Calendar.DAY_OF_YEAR);
        // return truncateTime(date1).equals(truncateTime(date2));
    }

    /**
     * Checks if two date objects are on the same day ignoring time.
     *
     * @param date1  the first date, not null
     * @param date2  the second date, not null
     * @return  true if they represent the same day
     * @throws NullPointerException if date1 or date2 is {@code null}.
     */
    public static boolean isSameDay(@Nonnull Date date1, @Nonnull Date date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        // return isSameDay(truncateTime(date1), truncateTime(date2));
        return truncateTime(date1).equals(truncateTime(date2));
    }

    /**
     * Checks if two date objects are on the same day ignoring time.
     *
     * @param date1  the first date, not null
     * @param date2  the second date, not null
     * @return  true if they represent the same day
     * @throws NullPointerException if date1 or date2 is {@code null}.
     */
    public static boolean isSameDay(@Nonnull LocalDateTime date1, @Nonnull LocalDateTime date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        return truncateTime(date1).equals(truncateTime(date2));
    }

    /**
     * Checks if two date objects are on the same day ignoring time.
     *
     * @param date1  the first date, not null
     * @param date2  the second date, not null
     * @return  true if they represent the same day
     * @throws NullPointerException if date1 or date2 is {@code null}.
     */
    public static boolean isSameDay(@Nonnull OffsetDateTime date1, @Nonnull OffsetDateTime date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        return truncateTime(date1).equals(truncateTime(date2));
    }

    /**
     * Checks if two date objects are on the same day ignoring time.
     *
     * @param date1  the first date, not null
     * @param date2  the second date, not null
     * @return  true if they represent the same day
     * @throws NullPointerException if date1 or date2 is {@code null}.
     */
    public static boolean isSameDay(@Nonnull ZonedDateTime date1, @Nonnull ZonedDateTime date2) {
        Ensure.notNull(date1);
        Ensure.notNull(date2);
        return truncateTime(date1).equals(truncateTime(date2));
    }

    /**
     *
     * @param clock
     * @return
     */
    public static Calendar calendarFromClock(Clock clock) {
        return GregorianCalendar.from(ZonedDateTime.now(clock));
    }
}
