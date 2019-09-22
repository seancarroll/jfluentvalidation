# jfluentvalidation

[![Build Status](https://travis-ci.org/seancarroll/jfluentvalidation.svg?branch=master)](https://travis-ci.org/seancarroll/jfluentvalidation)
[![codecov](https://codecov.io/gh/seancarroll/jfluentvalidation/branch/master/graph/badge.svg)](https://codecov.io/gh/seancarroll/jfluentvalidation)
[![Maintainability](https://api.codeclimate.com/v1/badges/7120177d6f1e69203073/maintainability)](https://codeclimate.com/github/seancarroll/jfluentvalidation/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/7120177d6f1e69203073/test_coverage)](https://codeclimate.com/github/seancarroll/jfluentvalidation/test_coverage)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=seancarroll_jfluentvalidation&metric=alert_status)](https://sonarcloud.io/dashboard?id=seancarroll_jfluentvalidation) 
[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=seancarroll_jfluentvalidation&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=seancarroll_jfluentvalidation)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=seancarroll_jfluentvalidation&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=seancarroll_jfluentvalidation)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=seancarroll_jfluentvalidation&metric=vulnerabilities)](https://sonarcloud.io/component_measures/metric/security_rating/list?id=seancarroll_jfluentvalidation)

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
* https://github.com/hibernate/hibernate-validator
