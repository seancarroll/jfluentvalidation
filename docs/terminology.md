## Terminology

Potentials ones to consider

* Validator
* Constraint
* Violation
* Validation(s)
* Validate
* Constraint Violation
* Rule(s)
* RuleSet
* RuleBuilder
* Hint (Spring Validation or is it Hibernate Validator?)
* PropertyValidator

Idea: Maybe use a sentence breakdown / anatomy structure diagram in the docs to showcase terminology/language used


TODO: add rule set, when, multiple custom messages
```java
Validator fullName = new Validator(Person.class)
    .ruleFor(p -> p.getForename()).notNull();

Validator address = new Validator(Person.class)
    .ruleFor(p -> p.getStreetName()).notNull();

Validator personValidator = new Validator(Person.class)
    .ruleFor(p -> p.getBirthday()).notNull()
    .include(fullName)
    .include(address);
```



Is there a common word or phrase for a list/set/collection of constraints?
- Constraint Set
- Set Constraint

Validator contains 
- class type of root object
- a collection of PropertyRules

PropertyRules / PropertyConstraints / SubjectRules / SubjectConstraints
- a subject which is a lambda function to access the field from the object being validated
- a collection of constraints for that subject

Constraint
- expression to be validated
- rule set
- message / custom message
- message args
- predicate to determine if/when we should check the constraint
