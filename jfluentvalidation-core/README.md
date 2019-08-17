# jfluentvalidation-core

Better version of bean validation / hibernate validation Programmatic constraint declaration https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/validator-specifics.html#example-constraint-mapping and something much better than Spring's ValidationUtils junk

Potential terminology  
* Validator
* Constraint
* Violation
* Validation(s)
* Validate
* Constraint Violation
* Rule(s)

Things to include 
* Ability to fail fast. Is this just a boolean or a mode/strategy?
* Message Localization/Internationalization
  * Integrates with Spring's Localization/Internationalization --> Check how hibernate or other libraries/frameworks do it
  * Integrates with Play/Dropwizard/etc
* Ability to chain multiple validations
* Custom messages with variable substitution / naming parameters
* Bean Validation Specification Integration (https://beanvalidation.org/1.1/spec/ / https://beanvalidation.org/2.0/spec/)
  * Integrates with Spring Validators
* Have a `validate` to return validation results and a `validateAndThrow` that throws an exception
* have a `toString` on validation results to get single string failures separated by new line. Allow users to pass in a custom separator
* FluentValidation Root Context Data?
* FluentValidation PreValidate?
* FluentValidation Callbacks - OnAnyFailure / OnFailure
* Do we want to support Async validation?

ModelMapper FieldMappingTest shouldMapConstantToDestinationField
ModelMapper StringMapping -- sourcePropertyPath
ModelMapper Lambda

Dozer BeanMappingBuilder


https://dzone.com/articles/hacking-lambda-expressions-in-java

http://in.relation.to/2016/04/14/emulating-property-literals-with-java-8-method-references/

http://benjiweber.co.uk/blog/2015/08/17/lambda-parameter-names-with-reflection/

https://github.com/OpenHFT/Chronicle-Wire/blob/master/src/main/java/net/openhft/chronicle/wire/WireSerializedLambda.java

https://stackoverflow.com/questions/9864300/how-to-get-method-object-in-java-without-using-method-string-names/22745127#22745127

https://github.com/cronn-de/reflection-util

https://stackoverflow.com/questions/47104098/using-bytebuddy-to-intercept-setter

https://www.lyh.me/lambda-serialization.html#.XCb-nRNKgWo



- TODO: need to override Subject methods on superclasses
- TODO: static methods to create constraints
- TODO: look at argona primitive data structures that avoid boxing/unboxing.
- TODO: move default messages to a constants class
- TODO: is there a better way than forcing constraints to call super to set default message?

Additional date constraints
- TODO: isInSameYearAs
- TODO: isInSameMonthAs
- TODO: isInSameDayAs
- TODO: isToday / isNotToday
- TODO: isEquals vs equals
- TODO: custom comparator
