# Scratch Pad

Not yet sure about package structure or naming.  
Not sure if terminology I prefer. I started with validation however I'm beginning to lean towards constraint(s). Going to start throwing stuff in constraints package.  

Do we want/need to have a context class that we add our constraint rules to? ie
```
// ConstrantContext
ValidationContext context = new ValidationContext();
context.addConstraint()....

// or something like 
ValidationContext context = new ValidationContext();
context.constraintFor(...)...
```


Given we are not executing the constraint validations when we add them I'm not sure it makes sense to call the self emulating generic type classes "Constraints". Right now to me it makes more sense to use the terminology from Google's Truth and call them "Subjects". 
Our constraints then would be classes or methods. 

## Style

### fluentvalidation Style


```java

public class PersonValidator extends AbstractValidator<Person> {
    
    public PersonValidator() {
        ruleFor(p -> p.getForename()).notNull();
        ruleFor(p -> p.getSurname()).notNull().length(0, 255);
    }
    
}

```

### Attempt at Avoiding the Class (Generic)

```java

Validator<Person> personValidator = new Validator<Person>()
    .ruleFor(p -> p.getForename()).notNull();

```

### Attempt at Avoiding the Class Type


```java

Validator personValidator = new Validator(Person.class);

```

If we use this style can we still set validators? Ex:

```java
Validator fullName = new Validator(Person.class)
    .ruleFor(p -> p.getForename()).notNull();

Validator address = new Validator(Person.class)
    .ruleFor(p -> p.getStreetName()).notNull();

Validator personValidator = new Validator(Person.class)
    .include(fullName)
    .include(address);
```
