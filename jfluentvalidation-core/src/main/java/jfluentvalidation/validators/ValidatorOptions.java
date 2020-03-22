package jfluentvalidation.validators;

import jfluentvalidation.internal.DefaultClockProvider;

import javax.validation.ClockProvider;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Global Validator runtime options...I dont really like the statics here
 */
public class ValidatorOptions {

    // Ideas of what to include
    // cascade mode -- does this need to be here or just something that we have a default for that can be overridden?
    // _messageFormatterFactory
    // error code resolver
    // ClockProvider
    // temporal_validation_tolerance

    public static ClockProvider CLOCK_PROVIDER = DefaultClockProvider.INSTANCE;
    public static FailureMode FAILURE_MODE = FailureMode.CONTINUE;
    public static Duration TEMPORAL_VALIDATION_TOLERANCE = Duration.ZERO;
    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static void setDateFormat(String format) {
        DATE_FORMAT = format;
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DATE_TIME_FORMATTER;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(DATE_FORMAT);
    }

    public static String format(Calendar cal) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat();
        simpleDateFormat.setCalendar(cal);
        return simpleDateFormat.format(cal.getTime());
    }

    // should this be similar to hibernate validator ValidatorFactoryBean
//    config.constraintValidatorFactory( createConstraintValidatorFactory( config ) );
//	config.messageInterpolator( createMessageInterpolator( config ) );
//	config.traversableResolver( createTraversableResolver( config ) );
//	config.parameterNameProvider( createParameterNameProvider( config ) );
//	config.clockProvider( createClockProvider( config ) );

    // private final ClockProvider clockProvider;
    // TODO: figure out what temporalValidationTolerance is for? I think I knew this at some point
    // Its because "present" is a bit tricky especially with any date/time that accounts for changes in seconds or below
    // private final Duration temporalValidationTolerance;

}
