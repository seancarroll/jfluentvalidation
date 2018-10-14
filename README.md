# jfluentvalidation

A fluent validation library for Java. 
Hopefully a better version of hibernate validation's [programmatic constraint declaration](https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/validator-specifics.html#example-constraint-mapping) or Spring's ValidatorUtils.

Inspired by 
* https://github.com/JeremySkinner/FluentValidation
* https://github.com/vanthoainguyen/NValidator
* https://github.com/neoremind/fluent-validator
* http://joel-costigliola.github.io/assertj/assertj-core.html
* https://github.com/google/truth


Things to include 
* Ability to fail fast. Is this just a boolean or a mode/strategy?
* Message Localization/Internationalization
  * Integrates with Spring's Localization/Internationalization --> Check how hibernate or other libraries/frameworks do it
  * Integrates with Play/Dropwizard/etc
* Ability to chain multiple validations
* Custom messages with variable substitution / naming parameters
* Bean Validation Specification Integration (https://beanvalidation.org/1.1/spec/ / https://beanvalidation.org/2.0/spec/)
  * Integrates with Spring Validators

## Research 

I kind of like Google Truth's ComparisonResult in https://github.com/google/truth/blob/master/core/src/main/java/com/google/common/truth/Subject.java

Potential terminology  
* Validator
* Constraint
* Violation
* Validation(s)
* Validate
* Constraint Violation
* Rule(s)


### Links
* https://stackoverflow.com/questions/1217228/what-is-the-java-equivalent-for-linq
* https://github.com/TrigerSoft/jaque
* https://code.google.com/archive/p/sbql4j/
* https://github.com/my2iu/Jinq
* https://www.geeksforgeeks.org/expression-tree/
* https://benjiweber.co.uk/blog/2013/12/28/typesafe-database-interaction-with-java-8/
* http://joel-costigliola.github.io/assertj/
* http://hamcrest.org/JavaHamcrest/
* http://joel-costigliola.github.io/assertj/assertj-core.html
* https://github.com/google/truth
* https://beanvalidation.org/2.0/


### FluentValdiation 
RuleFor  
RuleSet  
WithMessage  
https://github.com/JeremySkinner/FluentValidation/blob/de3717fe59bf62b10f21b52b06d427d90998d2e7/src/FluentValidation/DefaultValidatorExtensions.cs    
https://github.com/JeremySkinner/FluentValidation/blob/64b78d6bdc9595d221b4d56ce70a00e6de08aa4e/src/FluentValidation/Internal/RuleBuilder.cs  
https://github.com/JeremySkinner/FluentValidation/blob/de3717fe59bf62b10f21b52b06d427d90998d2e7/src/FluentValidation/Syntax.cs  
https://github.com/JeremySkinner/FluentValidation/blob/de3717fe59bf62b10f21b52b06d427d90998d2e7/src/FluentValidation/CollectionValidatorExtensions.cs  
https://github.com/JeremySkinner/FluentValidation/blob/a0fc54be347e0ffddc699d72f2a7a88441304cae/src/FluentValidation/InlineValidator.cs  
