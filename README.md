# jfluentvalidation

A fluent validation library for Java.

## System Requirements
JDK 8 or above.

## Examples

```java
    private class PersonValidator extends DefaultValidator<Person> {

        PersonValidator() {
            ruleForString(Person::getName).isNotEmpty().startsWith("s").length(0, 4);
            ruleForInteger(Person::getAge).isPositive();
            ruleForBoolean(Person::isMarried).isFalse();
            ruleForZonedDateTime(Person::getSignedIn).isAfter(ZonedDateTime.now().minusDays(1));
            ruleForMap(Person::getPets).isNotEmpty().forEachKey(isLowerCase()).forEachValue(length(0, 5));

            include(new PersonAgeValidator());

            ruleSet("address", () ->  {
                ruleForObject(Person::getAddress).isNotNull();
            });
        }
    }

    private static class PersonAgeValidator extends DefaultValidator<Person> {

        PersonAgeValidator() {
            ruleForInteger(Person::getAge).must(isOver18());
        }

        private Predicate<Integer> isOver18() {
            return age -> age > 18;
        }
    }
```

## Motivation

I'm personally a fan of validation via Hibernate Validator annotations however the number of built in validations is 
limited and I inevitably have to create custom validators or programmatic Spring validations if I'm working within a 
project using the Spring Framework. I avoid programmatic in Hibernate Validator at all costs.
When it comes to programmatic validations I've always felt these frameworks to be cumbersome and leave a lot to be desired. 

## Inspiration

This project was inspired by the following open source projects 

* https://github.com/JeremySkinner/FluentValidation
* http://joel-costigliola.github.io/assertj/assertj-core.html
* https://github.com/google/truth
