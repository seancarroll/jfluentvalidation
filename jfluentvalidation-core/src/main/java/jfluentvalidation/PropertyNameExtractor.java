package jfluentvalidation;

import jfluentvalidation.common.Primitives;
import jfluentvalidation.validators.InvalidPropertyException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Method;

public class PropertyNameExtractor {

    // TODO: cache for byte buddy proxies?

    private static final PropertyNameExtractor INSTANCE = new PropertyNameExtractor();

    public static PropertyNameExtractor getInstance() {
        return INSTANCE;
    }

    public <T> String getPropertyName(Class<T> type, SerializableFunction<T, ?> propertyFunction) {
        return propertyFunction.method().getName().startsWith("lambda")
            ? getNameViaByteBuddyProxy(type, propertyFunction)
            : getNameViaSerializableLambda(propertyFunction);
    }

    private <T> String getNameViaByteBuddyProxy(Class<T> type, SerializableFunction<T, ?> propertyFunction) {
        PropertyNameCapturingInterceptor interceptor = new PropertyNameCapturingInterceptor(propertyFunction);

        T entity =  createProxyInstance(type, interceptor);
        propertyFunction.apply(entity);

        return interceptor.getName();
    }

    private String getNameViaSerializableLambda(SerializableFunction propertyFunction) {
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


    private <T> T createProxyInstance(Class<?> type, PropertyNameCapturingInterceptor interceptor) {
        try {
            DynamicType.Builder<?> builder = new ByteBuddy().subclass(type.isInterface() ? Object.class : type);
            if (type.isInterface()) {
                builder = builder.implement(type);
            }

            Class<?> proxyType = builder
                .method(ElementMatchers.isGetter()).intercept(MethodDelegation.to(interceptor))
                .make()
                .load(PropertyNameExtractor.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();

            @SuppressWarnings("unchecked")
            Class<T> typed = (Class<T>) proxyType;

            Objenesis objenesis = new ObjenesisStd();
            return objenesis.newInstance(typed);
        } catch (Exception e) {
            throw new InvalidPropertyException("Couldn't instantiate proxy for property literal dereferencing", e);
        }
    }

    public class PropertyNameCapturingInterceptor {

        private final SerializableFunction serializableFunction;
        private String name;

        PropertyNameCapturingInterceptor(SerializableFunction serializableFunction) {
            this.serializableFunction = serializableFunction;
        }

        /**
         * Used by PropertyLiteralHelper#getPropertyNameCapturer
         * @param capturer
         * @param method
         * @return
         */
        @RuntimeType
        public Object intercept(@This Object capturer, @Origin Method method) {

            // TODO: crap...this works to successfully proxy nested fields but cant get property name of root

            if (!Primitives.isAssignable(method.getReturnType(), serializableFunction.method().getReturnType())) {
                return createProxyInstance(method.getReturnType(), this);
            }

            name = getPropertyName(method);

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
        }

        public String getName() {
            return name;
        }
    }
}
