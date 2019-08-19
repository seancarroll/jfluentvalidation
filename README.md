# jfluentvalidation

A fluent validation library for Java.

So what exactly does that mean? It means instead of building programmatic validators like

TODO: showcase hibernate validation solution

```java

```

TODO: showcase Spring's solution

```java

```

you build validators like ...

TODO: show our solution

```java

```

it would be a weird transition to go from this to the motivation section

how about we tell a fucking story!

Why the need for another validation library?

 
Hopefully an improved version of hibernate validation's [programmatic constraint declaration](https://docs.jboss.org/hibernate/validator/5.0/reference/en-US/html/validator-specifics.html#example-constraint-mapping) or Spring's ValidatorUtils.

JSR 380: Bean Validation 2.0
- https://www.jcp.org/en/jsr/detail?id=380
- https://beanvalidation.org/2.0/

## System Requirements
JDK 8 or above.

## Examples

TODO: Provide some real life examples of when you might prefer this over an annotation.  
Show validation method wrapped by annotation vs this format  

Potential examples
* Password strength - Need 3 classes of characters (letter, number, special character), upper and lower case, length
  

## Motivation

I'm personally a fan of annotation paradigm however this doesn't always work  
provide some examples

When it comes to programmatic validation (hibernate / spring) I've always felt these frameworks 
leave a lot to be desired / are cumbersome to use

Spring related...I dont always want to validate in the controller

I always thought that Fluent validation provided a easy to use API surface 

## Inspiration

This project was inspired by the following open source projects 

* https://github.com/JeremySkinner/FluentValidation
* http://joel-costigliola.github.io/assertj/assertj-core.html
* https://github.com/google/truth
