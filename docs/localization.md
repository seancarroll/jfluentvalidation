# Localization

Given one of our core goals is easy integration with applications that already use Hibernate validation I think it makes sense to use Hibernate validation's localization underlying components. 
Assuming of course they expose them. 
It might also make sense for us to have a pluggable system so that users can provide there own localization sub system.
It would also allow us to not force a hard dependency on hibernate which we could break out into a separate module. 
I would like to follow the [JSR 380: Bean Validation 2.0 specification](https://beanvalidation.org/2.0/) if they have specific rules for localization.

TODO: check to see what other localization/internationalization libraries exist that might be useful.

Is there a java specification outside of JSR 380? 

