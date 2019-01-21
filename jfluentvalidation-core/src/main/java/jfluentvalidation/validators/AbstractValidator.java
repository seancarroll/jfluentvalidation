package jfluentvalidation.validators;

import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.core.StringSubject;
import jfluentvalidation.core.Subject;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static net.bytebuddy.matcher.ElementMatchers.named;

//TODO: check these out
//import javax.validation.Constraint;
//import javax.validation.ConstraintValidator;

public abstract class AbstractValidator<T> {

    private static final Objenesis OBJENESIS = new ObjenesisStd();
    private static final String  fmt = "%24s: %s%n";

    private Object sourceConstant;
    public volatile T source;
    public T proxy;
    public T proxyType;
    // TODO: will likely need a wrapper/container around a subject
    private List<Subject<?, ?>> subjects = new ArrayList<>();

    // QUESTION: where do you belong?
    private Function<T, ?> func;

    // QUESTION:  Should we wrap in a Locale aware interpolator? What does spring do?
    // private MessageInterpolator messageInterpolator = new ResourceBundleMessageInterpolator();

    // AggregateResourceBundleLocator, CachingResourceBundleLocator, DelegatingResourceBundleLocator, PlatformResourceBundleLocator
    // springs MessageSourceResourceBundleLocator
    // Spring validationMessageSource
    // private ResourceBundleLocator resourceBundleLocator = new ResourceBundleLocator();


    // private final Errors proxyErrors = new Errors();
    // private final Errors errors = new Errors();
    Class<T> type;

    // TODO: should cache bytebuddy proxies either via a hashmap or bytebuddy TypeCache
    public StringSubject ruleFor(Function<T, String> func) {

        this.func = func;

        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];


        // Why does this work but not the below?
        String pn = PropertyLiteralHelper.getPropertyName(type, func);

        proxy = PropertyLiteralHelper.getPropertyNameCapturer(type);
        String p = PropertyLiteralHelper.getPropertyName(proxy, func);

        //Class<T>  type = (Class<T>) source.getClass();
        DynamicType.Builder<?> builder = new ByteBuddy().subclass(type.isInterface() ? Object.class : type);
        if (type.isInterface()) {
            builder = builder.implement(type);
        }

        Class<?> proxyType = new ByteBuddy().subclass( type )
            .implement(PropertyNameCapturer.class)
            .defineField("propertyName", String.class, Visibility.PRIVATE)
            .method(ElementMatchers.any()).intercept(MethodDelegation.to(PropertyNameCapturingInterceptor.class))
            .method(named("setPropertyName").or(named("getPropertyName"))).intercept(FieldAccessor.ofBeanProperty())
            .make()
            .load(AbstractValidator.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        Class<T> typed = null;
        String prop = "";
        String z = "";
        try {
            typed = (Class<T>) proxyType;
            // This allows me to get the property name. Is there a way I can do this when validate is called?
            // Do I need to wrap it in a proxy like this? Can I wrap the actual instance in a proxy? Can I avoid the proxy all together?
            T capturer = typed.newInstance();
            func.apply(capturer);

            prop = ( (PropertyNameCapturer) capturer ).getPropertyName();
            z = propName(proxyType, func);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        if (typed == null) {

        }

//        T capturer = getPropertyNameCapturer( type );
//        property.apply( capturer );
//        String propertyName = ( (PropertyLiteralCapturer) capturer ).getPropertyName();


        // https://stackoverflow.com/questions/36872794/bytebuddy-how-to-implement-field-access-interceptor
        // http://in.relation.to/2016/04/14/emulating-property-literals-with-java-8-method-references/

        // TODO: what to do with this?
        // fluentvalidation has PropertyRule which get member and a cached accessor (propertyFunc)
        // also includes...
        // lambda expression used to create the rule
        // function to get the cascade mode
        // type to Validate
        // container Type that owns the property
        // signature is for RuleFor is public IRuleBuilderInitial<T, TProperty> RuleFor<TProperty>(Expression<Func<T, TProperty>> expression)
        // and it returns RuleBuilder
        // RuleBuilder has a PropertyRule and Parent Validator
        // for us to get type will what I have for jmediator work? If not, https://stackoverflow.com/questions/3403909/get-generic-type-of-class-at-runtime
        StringSubject subject = new StringSubject(func);
        subjects.add(subject);
        return subject;
    }


    private static <T> String propName(Class<?> proxyType, Function<T, String> func) {
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

        // TODO: this shouldnt be here as well have one for each ruleFor
        Object o = func.apply(entity);
        List<ValidationFailure> failures = new ArrayList();
        for (Subject<?, ?> subject : subjects) {
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
        List<ValidationFailure> failures = new ArrayList();
        for (Subject subject : subjects) {

        }

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
