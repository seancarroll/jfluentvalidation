package jfluentvalidation.validators;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;

//https://in.relation.to/2016/04/14/emulating-property-literals-with-java-8-method-references/

// doesnt work for nested fields


// TODO: move into PropertyNameCapturer?
/**
 *
 */
public class PropertyNameCapturingInterceptor {

//    private final SerializableFunction serializableFunction;

//    public PropertyNameCapturingInterceptor(SerializableFunction serializableFunction) {
//        this.serializableFunction = serializableFunction;
//    }

    /**
     * Used by PropertyLiteralHelper#getPropertyNameCapturer
     * @param capturer
     * @param method
     * @return
     */
    @RuntimeType
    public static Object intercept(@This PropertyNameCapturer capturer, @Origin Method method) {
        String propertyName = getPropertyName(method);
        capturer.setPropertyName(propertyName);

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
        // TODO: this causes other failures
//         Objenesis objenesis = new ObjenesisStd();
//         return objenesis.newInstance(returnType);
//        return PropertyLiteralHelper.createProxyInstance(returnType);
    }

    /**
     *
     * @param method
     * @return
     */
    public static String getPropertyName(Method method) {
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
}
