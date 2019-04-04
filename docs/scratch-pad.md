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



Do we want to avoid hard-coded hibernate validator dependency? Spring does this via HibernateValidatorDelegate

SpringValidatorAdapter

MessageSourceResolvable

DefaultMessageCodesResolver






Ideas from the .NET FluentValidator library

PropertyRule.java
```java
package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PropertyRule implements ValidationRule {

    final List<PropertyValidator> validators = new ArrayList<>();
    // Func<CascadeMode> _cascadeModeThunk = () => ValidatorOptions.CascadeMode;
    String propertyDisplayName;
    String _propertyName;
    private String[] _ruleSet = new String[0];

    private Function<Object, Object> propertyFunc;
    private Class typeToValidate;

    public PropertyRule(Function<Object, Object> propertyFunc, Class typeToValidate) {
        this.propertyFunc = propertyFunc;
        this.typeToValidate = typeToValidate;
    }

//    /// <summary>
//    /// Creates a new property rule.
//    /// </summary>
//    /// <param name="member">Property</param>
//    /// <param name="propertyFunc">Function to get the property value</param>
//    /// <param name="expression">Lambda expression used to create the rule</param>
//    /// <param name="cascadeModeThunk">Function to get the cascade mode.</param>
//    /// <param name="typeToValidate">Type to validate</param>
//    /// <param name="containerType">Container type that owns the property</param>
//    public PropertyRule(MemberInfo member, Func<object, object> propertyFunc, LambdaExpression expression, Func<CascadeMode> cascadeModeThunk, Type typeToValidate, Type containerType) {
//        Member = member;
//        PropertyFunc = propertyFunc;
//        Expression = expression;
//        OnFailure = x => { };
//        TypeToValidate = typeToValidate;
//        this._cascadeModeThunk = cascadeModeThunk;
//
//        DependentRules = new List<IValidationRule>();
//        PropertyName = ValidatorOptions.PropertyNameResolver(containerType, member, expression);
//        DisplayName = new LazyStringSource(x =>  ValidatorOptions.DisplayNameResolver(containerType, member, expression));
//    }

//    /// <summary>
//    /// Creates a new property rule from a lambda expression.
//    /// </summary>
//    public static PropertyRule Create<T, TProperty>(Expression<Func<T, TProperty>> expression) {
//        return Create(expression, () => ValidatorOptions.CascadeMode);
//    }
//
//    /// <summary>
//    /// Creates a new property rule from a lambda expression.
//    /// </summary>
//    public static PropertyRule Create<T, TProperty>(Expression<Func<T, TProperty>> expression, Func<CascadeMode> cascadeModeThunk, bool bypassCache = false) {
//        var member = expression.GetMember();
//        var compiled = AccessorCache<T>.GetCachedAccessor(member, expression, bypassCache);
//        return new PropertyRule(member, compiled.CoerceToNonGeneric(), expression, cascadeModeThunk, typeof(TProperty), typeof(T));
//    }


    @Override
    public List<PropertyValidator> getValidators() {
        return validators;
    }

    @Override
    public String[] getRuleSet() {
        return new String[0];
    }

    @Override
    public void setRuleSet(String[] ruleSet) {

    }

    @Override
    public List<ValidationFailure> validate(ValidationContext context) {
        return null;
    }

    public Function<Object, Object> getPropertyFunc() {
        return propertyFunc;
    }

    public Class getTypeToValidate() {
        return typeToValidate;
    }

//    /// <summary>
//    /// Adds a validator to the rule.
//    /// </summary>
//    public void AddValidator(IPropertyValidator validator) {
//        CurrentValidator = validator;
//        _validators.Add(validator);
//    }


//    /// <summary>
//    /// Performs validation using a validation context and returns a collection of Validation Failures.
//    /// </summary>
//    /// <param name="context">Validation Context</param>
//    /// <returns>A collection of validation failures</returns>
//    public virtual IEnumerable<ValidationFailure> Validate(ValidationContext context) {
//        string displayName = GetDisplayName(context);
//
//        if (PropertyName == null && displayName == null) {
//            //No name has been specified. Assume this is a model-level rule, so we should use empty string instead.
//            displayName = string.Empty;
//        }
//
//        // Construct the full name of the property, taking into account overriden property names and the chain (if we're in a nested validator)
//        string propertyName = context.PropertyChain.BuildPropertyName(PropertyName ?? displayName);
//
//        // Ensure that this rule is allowed to run.
//        // The validatselector has the opportunity to veto this before any of the validators execute.
//        if (!context.Selector.CanExecute(this, propertyName, context)) {
//            yield break;
//        }
//
//        var cascade = _cascadeModeThunk();
//        bool hasAnyFailure = false;
//
//        // Invoke each validator and collect its results.
//        foreach (var validator in _validators) {
//            IEnumerable<ValidationFailure> results;
//            if (validator.ShouldValidateAsync(context))
//                results = InvokePropertyValidatorAsync(context, validator, propertyName, default(CancellationToken)).GetAwaiter().GetResult();
//				else
//                results = InvokePropertyValidator(context, validator, propertyName);
//
//                bool hasFailure = false;
//
//                foreach (var result in results) {
//                hasAnyFailure = true;
//                hasFailure = true;
//                yield return result;
//            }
//
//            // If there has been at least one failure, and our CascadeMode has been set to StopOnFirst
//            // then don't continue to the next rule
//            if (cascade == FluentValidation.CascadeMode.StopOnFirstFailure && hasFailure) {
//                break;
//            }
//        }
//
//        if (hasAnyFailure) {
//            // Callback if there has been at least one property validator failed.
//            OnFailure(context.InstanceToValidate);
//        }
//        else {
//            foreach (var dependentRule in DependentRules) {
//                foreach (var failure in dependentRule.Validate(context)) {
//                    yield return failure;
//                }
//            }
//        }
//    }
}
```

CollectionPropertyRule.java
```java
package jfluentvalidation.validators;

import java.util.function.Function;

public class CollectionPropertyRule<T> extends PropertyRule {

    public CollectionPropertyRule(Function<Object, Object> propertyFunc, Class typeToValidate) {
        super(propertyFunc, typeToValidate);
    }
}
```

PropertyValidator.java
```java
package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;

import java.util.List;

// TODO: what package should this live in?
// Name from fluentValidation. Can we think of a better name?
public interface PropertyValidator {

    List<ValidationFailure> validate(PropertyValidatorContext context);


    // Task<IEnumerable<ValidationFailure>> ValidateAsync(PropertyValidatorContext context, CancellationToken cancellation);

    // boolean ShouldValidateAsync(ValidationContext context);


    PropertyValidatorOptions getOptions();

}
```

PropertyValidatorContext.java
```java
package jfluentvalidation.validators;

// TODO: what package should this live in?
// Name from fluentValidation. Can we think of a better name?
public class PropertyValidatorContext {

//    private MessageFormatter _messageFormatter;
//    private readonly Lazy<object> _propertyValueContainer;
//
//    public ValidationContext ParentContext { get; private set; }
//    public PropertyRule Rule { get; private set; }
//    public string PropertyName { get; private set; }
//
//    public string DisplayName => Rule.GetDisplayName(ParentContext);
//
//    public object Instance => ParentContext.InstanceToValidate;
//
//    object IValidationContext.InstanceToValidate => ParentContext.InstanceToValidate;
//
//    public MessageFormatter MessageFormatter => _messageFormatter ?? (_messageFormatter = ValidatorOptions.MessageFormatterFactory());
//
//    //Lazily load the property value
//    //to allow the delegating validator to cancel validation before value is obtained
//    public object PropertyValue => _propertyValueContainer.Value;
//
//    // Explicit implementation so we don't have to expose the base interface.
//    IValidationContext IValidationContext.ParentContext => ParentContext;
//
//    public PropertyValidatorContext(ValidationContext parentContext, PropertyRule rule, string propertyName) {
//        ParentContext = parentContext;
//        Rule = rule;
//        PropertyName = propertyName;
//        _propertyValueContainer = new Lazy<object>( () => {
//            var value = rule.PropertyFunc(parentContext.InstanceToValidate);
//        if (rule.Transformer != null) value = rule.Transformer(value);
//        return value;
//			});
//    }
//
//    public PropertyValidatorContext(ValidationContext parentContext, PropertyRule rule, string propertyName, object propertyValue) {
//        ParentContext = parentContext;
//        Rule = rule;
//        PropertyName = propertyName;
//        _propertyValueContainer = new Lazy<object>(() => propertyValue);
//    }


}
```

PropertyValidatorOptions.java
```java
package jfluentvalidation.validators;

// TODO: what package should this live in?
// Name from fluentValidation. Can we think of a better name?
public class PropertyValidatorOptions {

//    private IStringSource _errorSource;
//    private IStringSource _errorCodeSource;
//
//    /// <summary>
//    /// Function used to retrieve custom state for the validator
//    /// </summary>
//    public Func<PropertyValidatorContext, object> CustomStateProvider { get; set; }
//
//    /// <summary>
//    /// Severity of error.
//    /// </summary>
//    public Severity Severity { get; set; }
//
//    /// <summary>
//    /// Retrieves the unformatted error message template.
//    /// </summary>
//    public IStringSource ErrorMessageSource {
//        get => _errorSource;
//        set => _errorSource = value ?? throw new ArgumentNullException(nameof(value));
//    }
//
//    /// <summary>
//    /// Retrieves the error code.
//    /// </summary>
//    public IStringSource ErrorCodeSource {
//        get => _errorCodeSource;
//        set => _errorCodeSource = value ?? throw new ArgumentNullException(nameof(value));
//    }
//
//    /// <summary>
//    /// Empty metadata.
//    /// </summary>
//    public static PropertyValidatorOptions Empty { get; } = new PropertyValidatorOptions {
//        _errorSource = new StaticStringSource(string.Empty),
//    };

}
```

ValidationContext.java
```java
package jfluentvalidation.validators;

// TODO: what package should this live in?
// Name from fluentValidation. Can we think of a better name?
public class ValidationContext {
}
```

ValidationRule.java
```java
package jfluentvalidation.validators;

import jfluentvalidation.ValidationFailure;

import java.util.List;

// TODO: what package should this live in?
// Name from fluentValidation. Can we think of a better name?
public interface ValidationRule {

    List<PropertyValidator> getValidators();

    String[] getRuleSet();

    void setRuleSet(String[] ruleSet);

    List<ValidationFailure> validate(ValidationContext context);

    // Task<IEnumerable<ValidationFailure>> ValidateAsync(ValidationContext context, CancellationToken cancellation);

    // TODO: this relates to my softconstraint
    // void ApplyCondition(Func<PropertyValidatorContext, bool> predicate, ApplyConditionTo applyConditionTo = ApplyConditionTo.AllValidators);

    // void ApplyAsyncCondition(Func<PropertyValidatorContext, CancellationToken, Task<bool>> predicate, ApplyConditionTo applyConditionTo = ApplyConditionTo.AllValidators);

}
```



FluentValidation PropertyValidator is the class that calls IsValid which returns `Enumerable.Empty<ValidationFailure>()` if valid otherwise calls `CreateValidationError(context)` which ends up building the ValidationFailure based on the context object

FluentValidation has 
- validator (IValidator/AbstractValidator)
- rule (IValidationRule/PropertyRule)
- rulebuilder
- propertyvalidator

