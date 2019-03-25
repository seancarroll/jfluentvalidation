package jfluentvalidation.validators;

import com.google.common.base.Splitter;
import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.core.*;
import net.jodah.typetools.TypeResolver;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

//TODO: check these out
//import javax.validation.Constraint;
//import javax.validation.ConstraintValidator;


public class DefaultValidator<T> {

    // TODO: I would prefer to not include guava so lets create our own splitter
    private static final Splitter RULESET_SPLITTER = Splitter.on(',').omitEmptyStrings().trimResults();

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


    // TODO: this doesnt work
//    public DefaultValidator() {
//        Function<Void, DefaultValidator<T>> f = (ignore) -> new DefaultValidator<T>();
//        Class<?>[] typeArguments = TypeResolver.resolveRawArguments(Function.class, f.getClass());
//        this.type = (Class<T>) typeArguments[0];
//        //this.type = (Class<T>) TypeResolver.resolveRawArguments(DefaultValidator.class, getClass())[0];
//        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
//    }


    // TODO: we shouldnt set type and proxy here
    // TODO: should cache bytebuddy proxies either via a hashmap or bytebuddy TypeCache
    public StringSubject ruleForString(Function<T, String> func) {
        this.type = (Class<T>) TypeResolver.resolveRawArguments(Function.class, func.getClass())[0];
        //this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        // TODO: I really dont like this. based on testing it needs to be static but can improve this via a cache or something
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        StringSubject subject = new StringSubject(func, propertyName);
        subjects.add(subject);
        return subject;
    }

    public CharSequenceSubject<?, ? extends CharSequence> ruleForCharSequence(Function<T, CharSequence> charSequence) {

        return null;
    }

    public CharSequenceSubject<?, ? extends StringBuilder> ruleForStringBuilder(Function<T, StringBuilder> stringBuilder) {
        return null;
    }

    public CharSequenceSubject<?, ? extends CharSequence> ruleForStringBuffer(Function<T, StringBuffer> stringBuffer) {
        return null;
    }

    // TODO: how to encapsulate type/proxy/propertyName/Subject?
    // stackify overcoming type erasure
    public IntegerSubject ruleForInteger(Function<T, Integer> func) {
        this.type = (Class<T>) TypeResolver.resolveRawArguments(Function.class, func.getClass())[0];
        //this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        // TODO: I really dont like this. based on testing it needs to be static but can improve this via a cache or something
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        IntegerSubject subject = new IntegerSubject(func, propertyName);
        subjects.add(subject);
        return subject;
    }

    public BooleanSubject ruleForBoolean(Function<T, Boolean> func) {
        this.type = (Class<T>) TypeResolver.resolveRawArguments(Function.class, func.getClass())[0];
        // this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        // TODO: I really dont like this. based on testing it needs to be static but can improve this via a cache or something
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        BooleanSubject subject = new BooleanSubject(func, propertyName);
        subjects.add(subject);
        return subject;
    }

    public ZonedDateTimeSubject ruleForZonedDateTime(Function<T, ZonedDateTime> func) {
        this.type = (Class<T>) TypeResolver.resolveRawArguments(Function.class, func.getClass())[0];
        // this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        // TODO: I really dont like this. based on testing it needs to be static but can improve this via a cache or something
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        ZonedDateTimeSubject subject = new ZonedDateTimeSubject(func, propertyName);
        subjects.add(subject);
        return subject;
    }

    public <K, V> MapSubject<K, V> ruleForMap(Function<T, Map<K, V>> func) {
        this.type = (Class<T>) TypeResolver.resolveRawArguments(Function.class, func.getClass())[0];
        // this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        // TODO: I really dont like this. based on testing it needs to be static but can improve this via a cache or something
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        MapSubject<K, V> subject = new MapSubject<>(func, propertyName);
        subjects.add(subject);
        return subject;
    }

    // TODO: how do we think we should implement a way to add constraints for each item in a collection?
    // One idea...
    public <R> IterableSubject<R> ruleForIterable(Function<T, Iterable<R>> func) {
        this.type = (Class<T>) TypeResolver.resolveRawArguments(Function.class, func.getClass())[0];
        // this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        // TODO: I really dont like this. based on testing it needs to be static but can improve this via a cache or something
        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);

        IterableSubject<R> subject = new IterableSubject<>(func, propertyName);
        subjects.add(subject);
        return subject;
    }


    // this follows Java Stream which has flatMap / flatMapToDouble / flatMapToInt / flatMapToLong and also includes a
    // forEach(Consumer<? super T> action) method
//    public <R> CollectionForEachSubject<R> forEach(Function<T, Iterable<R>> func) {
//        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//
//        // TODO: I really dont like this. based on testing it needs to be static but can improve this via a cache or something
//        this.proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
//        String propertyName = PropertyLiteralHelper.getPropertyName(proxy, func);
//
//        CollectionForEachSubject<R> subject = new CollectionForEachSubject<>(func, propertyName);
//        subjects.add(subject);
//        return subject;
//    }


    public DefaultValidator<T> include(DefaultValidator<T> validator) {
        // TODO: implement this
        return this;
    }

    // TODO: I think we can remove this
    // This was used when we were creating the byte buddy proxy in this class and passed it to this method
    // as the proxyType however we've since moved the logic out.
    private static <T> String getPropertyName(Class<?> proxyType, Function<T, String> func) {
        try {
            Class<T> typed = (Class<T>) proxyType;
            // This allows me to get the property name. Is there a way I can do this when validate is called?
            // Do I need to wrap it in a proxy like this? Can I wrap the actual instance in a proxy? Can I avoid the proxy all together?
            T capturer = typed.newInstance();
            func.apply(capturer);

            return ((PropertyNameCapturer) capturer).getPropertyName();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }


    // TODO: should this return ValidationResult or a list of validationfailures?
    // FluentValidator has IValidationRule return IEnumerable<ValidationFailure> while IValidator returns ValidationResult
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

    public List<ValidationFailure> validate(T entity, String ruleSet) {
        return validate(entity, RULESET_SPLITTER.splitToList(ruleSet));
    }

    public List<ValidationFailure> validate(T entity, List<String> ruleSet) {

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


//    public void ruleForEach(Function<T, Collection> func) {
//
//    }
//
//    public void ruleForEach(Function<T, Map> func) {
//
//    }


    //    TODO: a when clause similar to
//    When(x => x.Address != null, () => {
//        RuleFor(x => x.Address.Postcode).NotNull();
//        RuleFor(x => x.Address.Country.Name).NotNull().When(x => x.Address.Country != null);
//        RuleFor(x => x.Address.Line1).NotNull().When(x => x.Address.Line2 != null);
//    });
    public void when(Predicate<T> predicate, Consumer<T> consumer) {

    }

//    RuleSet("Names", () => {
//        RuleFor(x => x.Surname).NotEqual("foo");
//        RuleFor(x => x.Forename).NotEqual("foo");
//    });
    public void ruleSet(String ruleSetName, Consumer<T> consumer) {

    }

//    TODO: add unless clause

}
