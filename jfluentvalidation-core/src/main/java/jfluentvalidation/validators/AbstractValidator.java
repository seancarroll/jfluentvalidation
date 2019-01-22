package jfluentvalidation.validators;

import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.core.StringSubject;
import jfluentvalidation.core.Subject;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

//TODO: check these out
//import javax.validation.Constraint;
//import javax.validation.ConstraintValidator;

public abstract class AbstractValidator<T> {

    public T proxy; // source
    Class<T> type;

    // TODO: will likely need a wrapper/container around a subject
    private List<Subject<?, ?>> subjects = new ArrayList<>();

    // QUESTION:  Should we wrap in a Locale aware interpolator? What does spring do?
    // private MessageInterpolator messageInterpolator = new ResourceBundleMessageInterpolator();

    // AggregateResourceBundleLocator, CachingResourceBundleLocator, DelegatingResourceBundleLocator, PlatformResourceBundleLocator
    // springs MessageSourceResourceBundleLocator
    // Spring validationMessageSource
    // private ResourceBundleLocator resourceBundleLocator = new ResourceBundleLocator();

    // private final Errors proxyErrors = new Errors();
    // private final Errors errors = new Errors();


    // TODO: should cache bytebuddy proxies either via a hashmap or bytebuddy TypeCache
    public StringSubject ruleFor(Function<T, String> func) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        // TODO: I really dont like this. based on testing it needs to be static but can improve this via a cache or something
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        StringSubject subject = new StringSubject(func, propertyName);
        subjects.add(subject);
        return subject;
    }


    private static <T> String getPropertyName(Class<?> proxyType, Function<T, String> func) {
        try {
            Class<T> typed = (Class<T>) proxyType;
            // This allows me to get the property name. Is there a way I can do this when validate is called?
            // Do I need to wrap it in a proxy like this? Can I wrap the actual instance in a proxy? Can I avoid the proxy all together?
            T capturer = typed.newInstance();
            func.apply(capturer);

            return ( (PropertyNameCapturer) capturer ).getPropertyName();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public List<ValidationFailure> validate(T entity) {

        List<ValidationFailure> failures = new ArrayList();
        for (Subject<?, ?> subject : subjects) {
            Object o = subject.getPropertyFunc().apply(entity);
            for (Constraint c : subject.getConstraints()) {
                boolean isValid = c.isValid(o);
                if (!isValid) {
                    String errorMessage = c.getClass().getName() + "." + entity.getClass().getName() + ".";
                    failures.add(new ValidationFailure("", o));
                }
            }
        }

        return failures;
    }

    // TODO: do we want to allow passing a list of ruleSet? Would avoid the string split
    // Is that a good enough reason?
    public List<ValidationFailure> validate(T entity, String ruleSet) {

        List<ValidationFailure> failures = new ArrayList();
        for (Subject subject : subjects) {

        }

        return failures;
    }


    public void validateAndThrow(T entity) {
        List<ValidationFailure> failures = validate(entity);
        if (!failures.isEmpty()) {
            throw new ValidationException(failures);
        }
    }

    public void validateAndThrow(T entity, String ruleSet) {
        List<ValidationFailure> failures = new ArrayList();
        for (Subject subject : subjects) {

        }

        if (!failures.isEmpty()) {
            throw new ValidationException(failures);
        }
    }

}
