package jfluentvalidation.validators;

import jfluentvalidation.ValidationException;
import jfluentvalidation.ValidationFailure;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.core.StringSubject;
import jfluentvalidation.core.Subject;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.System.out;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.not;

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
    ExplicitMappingInterceptor interceptor = new ExplicitMappingInterceptor();

    private static final ElementMatcher<? super MethodDescription> METHOD_FILTER = not(named("hashCode").or(named("equals")));

    private static final Method PRIVATE_LOOKUP_IN;
    private static final Object LOOKUP;

    static {
        Method privateLookupIn;
        Object lookup;
        try {
            Class<?> methodHandles = Class.forName("java.lang.invoke.MethodHandles");
            lookup = methodHandles.getMethod("lookup").invoke(null);
            privateLookupIn = methodHandles.getMethod(
                "privateLookupIn",
                Class.class,
                Class.forName("java.lang.invoke.MethodHandles$Lookup")
            );
        } catch (Exception e) {
            privateLookupIn = null;
            lookup = null;
        }
        PRIVATE_LOOKUP_IN = privateLookupIn;
        LOOKUP = lookup;
    }

    private static <T> ClassLoadingStrategy<ClassLoader> chooseClassLoadingStrategy(Class<T> type) {
        try {
            final ClassLoadingStrategy<ClassLoader> strategy;
            if (ClassInjector.UsingLookup.isAvailable() && PRIVATE_LOOKUP_IN != null && LOOKUP != null) {
                Object privateLookup = PRIVATE_LOOKUP_IN.invoke(null, type, LOOKUP);
                strategy = ClassLoadingStrategy.UsingLookup.of(privateLookup);
            } else if (ClassInjector.UsingReflection.isAvailable()) {
                strategy = ClassLoadingStrategy.Default.INJECTION;
            } else {
                throw new IllegalStateException("No code generation strategy available");
            }
            return strategy;
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Failed to invoke 'privateLookupIn' method from java.lang.invoke.MethodHandles$Lookup.", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to invoke 'privateLookupIn' method from java.lang.invoke.MethodHandles$Lookup.", e);
        }
    }

    public String ruleFor(Supplier<String> supplier) {
        return supplier.get();
    }

    public StringSubject ruleFor(Function<T, String> func) {

        this.func = func;

        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        //Class<T>  type = (Class<T>) source.getClass();
        DynamicType.Builder<?> builder = new ByteBuddy().subclass(type.isInterface() ? Object.class : type);
        if (type.isInterface()) {
            builder = builder.implement(type);
        }

        Class<?> proxyType = builder
            .implement(PropertyNameCapturer.class)
            .defineField("propertyName", String.class, Visibility.PRIVATE)
            .method(ElementMatchers.any()).intercept(MethodDelegation.to(PropertyNameCapturingInterceptor.class))
            .method(named("setPropertyName").or(named("getPropertyName"))).intercept(FieldAccessor.ofBeanProperty())
            .make()
            .load(PropertyNameCapturer.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        Class<T> typed = null;
        try {
            typed = (Class<T>) proxyType;
            // This allows me to get the property name. Is there a way I can do this when validate is called?
            // Do I need to wrap it in a proxy like this? Can I wrap the actual instance in a proxy? Can I avoid the proxy all together?
            func.apply(typed.newInstance());
        } catch (Exception ex) {
            System.out.println(ex);
        }

        if (typed == null) {

        }

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


        // originally was in the constructor
//        Class<T> type = (Class<T>)source.getClass();
//
//        final DynamicType.Unloaded<T> unloaded = new ByteBuddy()
//            .subclass((Class<T>)source.getClass())
//            .method(METHOD_FILTER)
//            .intercept(InvocationHandlerAdapter.of(interceptor))
//            .make();
//
//        final ClassLoadingStrategy<ClassLoader> classLoadingStrategy = chooseClassLoadingStrategy(type);
//
//        if (classLoadingStrategy != null) {
//            proxy = OBJENESIS.newInstance(unloaded
//                //.load(useOSGiClassLoaderBridging ? BridgeClassLoaderFactory.getClassLoader(type) : type.getClassLoader(), classLoadingStrategy)
//                .load(type.getClassLoader(), classLoadingStrategy)
//                .getLoaded());
//        } else {
//            proxy = OBJENESIS.newInstance(unloaded
//                .load(type.getClassLoader())
//                .getLoaded());
//        }
//
//        final Class<?>  loaded = new ByteBuddy()
//            .subclass((Class<T>)source.getClass())
//            .method(METHOD_FILTER)
//            .intercept(InvocationHandlerAdapter.of(interceptor))
//            .make()
//            .load(PropertyNameCapturer.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
//            .getLoaded();
//
//        try {
//            Class<T> typed = (Class<T>) loaded;
//            proxyType = typed.newInstance();
//            if (proxyType != null) {
//
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
    }

    public void ruleFor(String s) {
        try {
            //Class<?> c = Class.forName(args[0]);
            Method[] allMethods = getClass().getSuperclass().getDeclaredMethods();
            for (Method m : allMethods) {
                out.format("%s%n", m.toGenericString());

                out.format(fmt, "ReturnType", m.getReturnType());
                out.format(fmt, "GenericReturnType", m.getGenericReturnType());

                Parameter[] ps  = m.getParameters();
                for (int i = 0; i < ps.length; i++) {
                    out.println(ps[i].getName());
                }

                Class<?>[] pType  = m.getParameterTypes();
                Type[] gpType = m.getGenericParameterTypes();
                for (int i = 0; i < pType.length; i++) {
                    out.format(fmt,"ParameterType", pType[i]);
                    out.format(fmt,"GenericParameterType", gpType[i]);
                }

                Class<?>[] xType  = m.getExceptionTypes();
                Type[] gxType = m.getGenericExceptionTypes();
                for (int i = 0; i < xType.length; i++) {
                    out.format(fmt,"ExceptionType", xType[i]);
                    out.format(fmt,"GenericExceptionType", gxType[i]);
                }
            }

            // production code should handle these exceptions more gracefully
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public List<ValidationFailure> validate(T entity) {
        getMembers((Class<T>) entity.getClass());

        Object o = func.apply(entity);
        List<ValidationFailure> failures = new ArrayList();
        for (Subject<?, ?> subject : subjects) {
            for (Constraint c : subject.getConstraints()) {
                boolean isValid = c.isValid(o);
                if (!isValid) {
                    failures.add(new ValidationFailure("", o));
                }
            }
        }

        return failures;
    }

    // TODO: do we want to allow passing a list of ruleSet? Would avoid the string split
    // Is that a good enough reason?
    public List<ValidationFailure> validate(T entity, String ruleSet) {
        getMembers((Class<T>) entity.getClass());

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

    public T getMembers(Class<T> type) {
        DynamicType.Builder<?> builder = new ByteBuddy().subclass(type.isInterface() ? Object.class : type);
        if (type.isInterface()) {
            builder = builder.implement(type);
        }

        Class<?> proxyType = builder
            .implement(PropertyNameCapturer.class)
            .defineField("propertyName", String.class, Visibility.PRIVATE)
            .method(ElementMatchers.any()).intercept(MethodDelegation.to(PropertyNameCapturingInterceptor.class))
            .method(named("setPropertyName").or(named("getPropertyName"))).intercept(FieldAccessor.ofBeanProperty())
            .make()
            .load(PropertyNameCapturer.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        try {
            Class<T> typed = (Class<T>) proxyType;
            return typed.newInstance();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }


    public final class ExplicitMappingInterceptor implements InvocationHandler {
        private final Map<String, Object> methodProxies = new HashMap<>();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            if (args.length == 1) {
                sourceConstant = args[0];
                if (sourceConstant != null && sourceConstant == source) {
                    //errors.missingSource();
                }
            }
            return methodProxies.get(method.getName());
        }
    }
}
