# Features

## Potential

Potential things to include

* Ability to chain multiple validations
* When method that applies validation(s) conditionally (see fluent validator DelegatingValidator) maybe we call this a Soft Constraint
* Allow user to pass a custom validation to validate. 
* Allow user to pass in a predicate as a validator
* Apply constraints for each item in a collection (list/array/set/etc). Similar to fluentvalidation's RuleForEach. 
this preferably should also allow a way to optionally include or exclude certain items in the collection from being 
validated fluentvalidation uses `.where`
* Have a `validate` to return validation results and a `validateAndThrow` that throws an exception
* Do we want to have something similar to hibernate validator hints or fluentvalidations ruleset?
* PreValidate?
* Validation Callbacks - OnAnyFailure / OnFailure
* have a `toString` on validation results to get single string failures separated by new line. Allow users to pass in a custom separator
* Ability to fail fast. Is this just a boolean or a mode/strategy? 
CascadeMode is not my favorite name. 
Also would there be more than two? Hibernate validator just has failFast (boolean)
* Message Localization/Internationalization
  * Integrates with Spring's Localization/Internationalization --> Check how hibernate or other libraries/frameworks do it
  * Integrates with Dropwizard/Play/etc
* Custom messages with variable substitution / naming parameters
* Custom error codes
* Bean Validation Specification Integration (https://beanvalidation.org/1.1/spec/ / https://beanvalidation.org/2.0/spec/)
  * Integrates with Spring Validators

