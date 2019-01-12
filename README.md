# jfluentvalidation

A fluent validation library for Java. 
Hopefully an improved version of hibernate validation's [programmatic constraint declaration](https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/validator-specifics.html#example-constraint-mapping) or Spring's ValidatorUtils.

JSR 380: Bean Validation 2.0
- https://www.jcp.org/en/jsr/detail?id=380
- https://beanvalidation.org/2.0/


Inspired by 
* https://github.com/JeremySkinner/FluentValidation
* https://github.com/vanthoainguyen/NValidator
* https://github.com/neoremind/fluent-validator
* http://joel-costigliola.github.io/assertj/assertj-core.html -- Check license
* https://github.com/google/truth


## Research 

I kind of like Google Truth's ComparisonResult in https://github.com/google/truth/blob/master/core/src/main/java/com/google/common/truth/Subject.java


## Questions

* What's the top level entry point?


## Examples

TODO: Provide some real life examples of when you might prefer this over an annotation.  
Show validation method wrapped by annotation vs this format  

Potential examples
* Password strength - Need 3 classes of characters (letter, number, special character), upper and lower case, length
  

