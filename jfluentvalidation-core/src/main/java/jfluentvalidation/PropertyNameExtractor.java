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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class PropertyNameExtractor {

    private static final PropertyNameExtractor INSTANCE = new PropertyNameExtractor(false);
    private static final Map<Class<?>, ByteBuddyPropertyNameCaptor<?>> PROXY_EXTRACTOR_CACHE = new ConcurrentHashMap<>();
    private final boolean enableCache;

    public static PropertyNameExtractor getInstance() {
        return INSTANCE;
    }

    public PropertyNameExtractor(boolean enableCache) {
        this.enableCache = enableCache;
    }

    /**
     *
     * @param type
     * @param propertyFunction
     * @param <T>
     * @return
     */
    public <T> String getPropertyName(Class<T> type, SerializableFunction<T, ?> propertyFunction) {
        return propertyFunction.method().getName().startsWith("lambda")
            ? getNameViaByteBuddyProxy(type, propertyFunction)
            : getNameViaSerializableLambda(propertyFunction);
    }

    private <T> String getNameViaByteBuddyProxy(Class<T> type, SerializableFunction<T, ?> propertyFunction) {
        final ByteBuddyPropertyNameCaptor<T> captor;
        if (enableCache) {
            captor = (ByteBuddyPropertyNameCaptor<T>) PROXY_EXTRACTOR_CACHE.computeIfAbsent(
                type,
                ignore -> new ByteBuddyPropertyNameCaptor(type, propertyFunction)
            );
        } else {
            captor = new ByteBuddyPropertyNameCaptor(type, propertyFunction);
        }

        return captor.capture();
    }

    private String getNameViaSerializableLambda(SerializableFunction<?, ?> propertyFunction) {
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

        private final SerializableFunction<?, ?> serializableFunction;
        private String name;

        PropertyNameCapturingInterceptor(SerializableFunction<?, ?> serializableFunction) {
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
            if (!Primitives.isAssignable(method.getReturnType(), serializableFunction.method().getReturnType())) {
                return createProxyInstance(method.getReturnType(), this);
            }

            name = getPropertyName(method);

            Class<?> returnType = method.getReturnType();
            if (returnType == byte.class) {
                return (byte) 0;
            } else if (returnType == char.class) {
                return (char) 0;
            } else if (returnType == double.class) {
                return (double) 0;
            } else if (returnType == float.class) {
                return (float) 0;
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

    class ByteBuddyPropertyNameCaptor<T> {
        private final SerializableFunction<T, ?> propertyFunction;
        private final T proxy;
        private final PropertyNameCapturingInterceptor interceptor;

        public ByteBuddyPropertyNameCaptor(Class<T> type, SerializableFunction<T, ?> propertyFunction) {
            this.propertyFunction = propertyFunction;
            this.interceptor = new PropertyNameCapturingInterceptor(propertyFunction);
            this.proxy = createProxyInstance(type, interceptor);
        }

        String capture() {
            propertyFunction.apply(proxy);
            return interceptor.name;
        }
    }
}
