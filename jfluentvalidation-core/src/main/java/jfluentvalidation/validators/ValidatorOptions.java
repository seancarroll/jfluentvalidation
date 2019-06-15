package jfluentvalidation.validators;

public class ValidatorOptions {

    // Ideas of what to include
    // cascade mode -- does this need to be here or just something that we have a default for that can be overridden?
    // _messageFormatterFactory
    // error code resolver
    // ClockProvider

    // should this be similar to hibernate validator ValidatorFactoryBean
//    config.constraintValidatorFactory( createConstraintValidatorFactory( config ) );
//	config.messageInterpolator( createMessageInterpolator( config ) );
//	config.traversableResolver( createTraversableResolver( config ) );
//	config.parameterNameProvider( createParameterNameProvider( config ) );
//	config.clockProvider( createClockProvider( config ) );

    // private final ClockProvider clockProvider;
    // TODO: figure out what temporalValidationTolerance is for? I think I knew this at some point
    // private final Duration temporalValidationTolerance;

}
