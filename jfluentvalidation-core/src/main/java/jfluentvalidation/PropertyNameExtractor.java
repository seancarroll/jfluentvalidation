package jfluentvalidation;

import jfluentvalidation.validators.InvalidPropertyException;
import jfluentvalidation.validators.PropertyLiteralHelper;
import jfluentvalidation.validators.PropertyNameInterceptorResult;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;

// https://stackoverflow.com/questions/34925524/java-8-convert-lambda-to-a-method-instance-with-closure-included/34929760#34929760
// https://stackoverflow.com/questions/34925524/java-8-convert-lambda-to-a-method-instance-with-closure-included
public class PropertyNameExtractor {

    // TODO: cache for byte buddy proxies
    // TODO: lambda expression / method reference

    private final Map<Class<?>, Object> byteBuddyProxyCache = new HashMap<>();
//    private final SerializableFunction propertyFunction;
//
//    public PropertyNameExtractor(SerializableFunction propertyFunction) {
//        this.propertyFunction = propertyFunction;
//    }

    private static final PropertyNameExtractor INSTANCE = new PropertyNameExtractor();

    public static PropertyNameExtractor getInstance() {
        return INSTANCE;
    }

    public String getPropertyName(Class<?> type, SerializableFunction propertyFunction) {
        return propertyFunction.method().getName().startsWith("lambda")
            ? getNameViaByteBuddyProxy(type, propertyFunction)
            : getNameViaSerializableLambda(type, propertyFunction);
    }

    private String getNameViaByteBuddyProxy(Class<?> type, SerializableFunction propertyFunction) {
        PropertyNameInterceptorResult result = new PropertyNameInterceptorResult();
        PropertyNameCapturingInterceptor interceptor = new PropertyNameCapturingInterceptor(propertyFunction, result);

        // TODO: is it possible to cache proxies?
        // Object entity = byteBuddyProxyCache.computeIfAbsent(type, k -> createProxyInstance(type, interceptor));
        Object entity =  createProxyInstance(type, interceptor);
        propertyFunction.apply(entity);
        String name = ((PropertyNameCapturer) entity).getFluentValidationPropertyName();
//        PropertyNameCapturer e = (PropertyNameCapturer) entity;
//        if (clazz != propertyFunction.method().getReturnType()) {
//            e = createProxyInstance(clazz, interceptor);
//            //propertyFunction.apply(e);
//        }

//        return e.getPropertyName();

//        Result r = new Result();
//        e.setCallBack(name -> r.setName(name));
//        propertyFunction.apply(e);
        //return r.getName();

        return interceptor.name;
//         return ((PropertyNameCapturer) entity).getPropertyName();
    }

    private String getNameViaSerializableLambda(Class<?> type, SerializableFunction propertyFunction) {
        return getPropertyName(propertyFunction.method());
    }


    /**
     *
     * @param method
     * @return
     */
    private static String getPropertyName(Method method) {

        final boolean hasGetterSignature = method.getParameterTypes().length == 0 && method.getReturnType() != null;

        String name = method.getName();
        final String propName;
        if (hasGetterSignature) {
            if (name.startsWith("get")) {
                propName = name.substring(3, 4).toLowerCase() + name.substring(4);
            } else if (name.startsWith("is")) {
                propName = name.substring(2, 3).toLowerCase() + name.substring(3);
            } else {
                // TODO: should be just return name? Support getting value from field or property that does not conform to java bean style?
                propName = name;
            }
        } else {
            throw new InvalidPropertyException("Only property getter methods are expected to be passed");
        }

        return propName;
    }


    public <T> T createProxyInstance(Class<?> type, PropertyNameCapturingInterceptor interceptor) {
        // TODO: how can we create an instance even when no-arg constructor is not defined.
        try {
            DynamicType.Builder<?> builder = new ByteBuddy().subclass(type.isInterface() ? Object.class : type);
            if (type.isInterface()) {
                builder = builder.implement(type);
            }

            Class<?> proxyType = builder //new ByteBuddy().subclass( type )
                .implement(PropertyNameCapturer.class)
                .defineField("fluentValidationPropertyName", String.class, Visibility.PRIVATE)
                .method(ElementMatchers.isGetter().and(ElementMatchers.not(named("getFluentValidationPropertyName")))).intercept(MethodDelegation.to(interceptor))
                .method(named("setFluentValidationPropertyName").or(named("getFluentValidationPropertyName"))).intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(PropertyLiteralHelper.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();

            // TODO: is there a way to either not force the class to have a public no-arg constructor?
            // It would be nice if we could support private no-arg constructor at the very least but my preference
            // would be not to require anything.
            // I'm still investigating this but one way is to use Objenesis to create the instance.
            // from https://stackoverflow.com/questions/50478383/byte-buddy-instantiate-class-without-parameters-for-constructor
            // and https://stackoverflow.com/questions/23827311/create-a-dynamic-proxy-for-a-class-without-no-argument-constructor
            // This appears to work. I would still like to understand potential way to just use byte buddy.
            // I'm thinking I could check if the type has a public constructor and if not have byte buddy create one
            @SuppressWarnings("unchecked")
            Class<T> typed = (Class<T>) proxyType;

            Objenesis objenesis = new ObjenesisStd();
            return objenesis.newInstance(typed);
        } catch (Exception e) { //InstantiationException | IllegalAccessException e) {
            throw new InvalidPropertyException("Couldn't instantiate proxy for property literal dereferencing", e);
        }
    }


    public interface PropertyNameCapturer {

        String getFluentValidationPropertyName();

        void setFluentValidationPropertyName(String propertyName);

    }


    public class PropertyNameCapturingInterceptor {

        private final SerializableFunction serializableFunction;
        private final PropertyNameInterceptorResult result;
        public String name;

        public PropertyNameCapturingInterceptor(SerializableFunction serializableFunction, PropertyNameInterceptorResult result) {
            this.serializableFunction = serializableFunction;
            this.result = result;
        }

        /**
         * Used by PropertyLiteralHelper#getPropertyNameCapturer
         * @param capturer
         * @param method
         * @return
         */
        @RuntimeType
        public Object intercept(@This PropertyNameCapturer capturer, @Origin Method method) {

            // TODO: crap...this works to successfully proxy nested fields but cant get property name of root
            if (method.getReturnType() != serializableFunction.method().getReturnType()) {
                return createProxyInstance(method.getReturnType(), this);
            }

            String propertyName = getPropertyName(method);
            capturer.setFluentValidationPropertyName(propertyName);
            name = propertyName;
            result.setName(propertyName);

            Class<?> returnType = method.getReturnType();
            if (returnType == byte.class) {
                return (byte) 0;
            } else if (returnType == char.class) {
                return (char) 0;
            } else if (returnType == int.class) {
                return 0;
            } else if (returnType == short.class) {
                return (short) 0;
            } else if (returnType == boolean.class) {
                return false;
            }

            return null;
//            Objenesis objenesis = new ObjenesisStd();
//            return objenesis.newInstance(returnType);
        }
    }
}
