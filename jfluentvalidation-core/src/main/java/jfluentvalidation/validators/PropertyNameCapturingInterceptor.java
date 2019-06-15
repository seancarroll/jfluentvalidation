package jfluentvalidation.validators;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;

// TODO: move into PropertyNameCapturer?
/**
 *
 */
public class PropertyNameCapturingInterceptor {

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
