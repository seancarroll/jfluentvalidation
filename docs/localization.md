# Localization

Given one of our core goals is easy integration with applications that already use Hibernate validation I think it makes sense to use Hibernate validation's localization underlying components. 
Assuming of course they expose them. 
It might also make sense for us to have a pluggable system so that users can provide there own localization sub system.
It would also allow us to not force a hard dependency on hibernate which we could break out into a separate module. 
I would like to follow the [JSR 380: Bean Validation 2.0 specification](https://beanvalidation.org/2.0/) if they have specific rules for localization.

TODO: check to see what other localization/internationalization libraries exist that might be useful.

Is there a java specification outside of JSR 380? 

JSR 341: Expression Language 3.0 https://jcp.org/en/jsr/detail?id=341



https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-custom-message-interpolation

Configuration#getDefaultMessageInterpolator()

ResourceBundleMessageInterpolator

ResourceBundleLocator

validationMessageSource - is this responsible for the localization strategy of finding match based on removing periods?



Validation message properties files can be found in: hibernate-validator/engine/src/main/resources/org/hibernate/validator

Hibernate has a ValidationMessages class under hibernate-validator/engine/test/org/hibernate/validator/ValidationMessages.java 
which extends ResourceBundle. Might be worth looking at in more depth to see if it helps us at all
