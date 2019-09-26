package jfluentvalidation.validators;

import jfluentvalidation.internal.DefaultClockProvider;

import javax.validation.ClockProvider;
import java.time.Duration;

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

    public static final ClockProvider CLOCK_PROVIDER = DefaultClockProvider.INSTANCE;
    public static final FailureMode FAILURE_MODE = FailureMode.CONTINUE;
    public static final Duration TEMPORAL_VALIDATION_TOLERANCE = Duration.ZERO;

    // TODO: date format option for error messages

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
