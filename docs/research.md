# Research


## Emulating Self Types using Java Generics

Emulating 'self types' using Java Generics to simplify fluent API implementation  
Link(s) 
* http://bit.ly/1IZIRcY
* https://www.sitepoint.com/self-types-with-javas-generics/
* https://blog.joda.org/2007/08/java-7-self-types_1953.html
* https://www.artima.com/weblogs/viewpost.jsp?thread=136394
* https://medium.com/@jerzy.chalupski/emulating-self-types-in-kotlin-d64fe8ea2e62

Projects that implement pattern
* [AssertJ](http://joel-costigliola.github.io/assertj/)
* [Google Truth](https://github.com/google/truth) 
* [FEST Assertions](https://github.com/alexruiz/fest-assert-2.x)


## Links
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
// TODO: add guava code that uses a similar pattern --> I think guava has this pattern as well


## FluentValdiation 
RuleFor  
RuleSet  
WithMessage  
https://github.com/JeremySkinner/FluentValidation/blob/de3717fe59bf62b10f21b52b06d427d90998d2e7/src/FluentValidation/DefaultValidatorExtensions.cs    
https://github.com/JeremySkinner/FluentValidation/blob/64b78d6bdc9595d221b4d56ce70a00e6de08aa4e/src/FluentValidation/Internal/RuleBuilder.cs  
https://github.com/JeremySkinner/FluentValidation/blob/de3717fe59bf62b10f21b52b06d427d90998d2e7/src/FluentValidation/Syntax.cs  
https://github.com/JeremySkinner/FluentValidation/blob/de3717fe59bf62b10f21b52b06d427d90998d2e7/src/FluentValidation/CollectionValidatorExtensions.cs  
https://github.com/JeremySkinner/FluentValidation/blob/a0fc54be347e0ffddc699d72f2a7a88441304cae/src/FluentValidation/InlineValidator.cs  


## Additional Constraints

* Numeric
* isZero
* isNotZero
* isOne
* isNotOne
* isBetween
* isStrictlyBetween
* isCloseTo (BigDecimal)
* isNotCloseTo (BigDecimal)
* Comparator



## Additional Research

- callsite information
- intercepting method (byte buddy)
- lambda expressions and lambda meta factory
- serializable lambdas
- what projects might do something similiar
-  dozer
-  modelmapper
-  jooq?
-  feign?


Review validation libraries 
- review hibernate validation (with & without annotations)
- review Spring Validator
- Framework (Dropwizard / Vertx / Play) Validation 
- Apache commons validator
- vavr validation
- neoremind fluent-validator
- validly


maybe we just have users pass in string parameter name?

what does guava do for StringBuilder?



ParameterMessageInterpolator vs javax.el.ExpressionFactory


https://stackoverflow.com/questions/37719089/how-to-efficiently-wrap-pojo-with-bytebuddy
https://stackoverflow.com/questions/47104098/using-bytebuddy-to-intercept-setter
https://github.com/raphw/byte-buddy/issues/323
https://groups.google.com/forum/#!topic/byte-buddy/nBuGgkW63vQ
https://stackoverflow.com/questions/40292185/how-to-create-a-dynamic-proxy-using-bytebuddy

https://stackoverflow.com/questions/36872794/bytebuddy-how-to-implement-field-access-interceptor
http://in.relation.to/2016/04/14/emulating-property-literals-with-java-8-method-references/



// TODO: what to do with Subject? What should be contained in Subject?
// fluentvalidation has PropertyRule which get member and a cached accessor (propertyFunc)
// also includes...
// lambda expression used to create the rule
// function to get the cascade mode
// type to Validate
// container Type that owns the property
// signature is for RuleFor is public IRuleBuilderInitial<T, TProperty> RuleFor<TProperty>(Expression<Func<T, TProperty>> expression)
// and it returns RuleBuilder
// RuleBuilder has a PropertyRule and Parent Validator
// for us to get type will what I have for jmediator work? If not, https://stackoverflow.com/questions/3403909/get-generic-type-of-class-at-runtime
