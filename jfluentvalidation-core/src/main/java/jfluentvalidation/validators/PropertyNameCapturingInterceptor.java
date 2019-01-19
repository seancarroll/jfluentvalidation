package jfluentvalidation.validators;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;

public class PropertyNameCapturingInterceptor {

    @RuntimeType
    public static Object intercept(@This PropertyNameCapturer capturer, @Origin Method method) {
        capturer.setPropertyName( getPropertyName( method ) );

        if ( method.getReturnType() == byte.class ) {
            return (byte) 0;
        }
//        else if ( ... ) {// ... handle all primitve types
//            // ...
//        } else {
//            return null;
//        }
        return null;
    }

    public static String getPropertyName(Method method) {
        final boolean hasGetterSignature = method.getParameterTypes().length == 0
            && method.getReturnType() != null;

        String name = method.getName();
        String propName = null;

        if (hasGetterSignature) {
            if (name.startsWith("get") && hasGetterSignature) {
                propName = name.substring(3, 4).toLowerCase() + name.substring(4);
            } else if (name.startsWith("is") && hasGetterSignature) {
                propName = name.substring(2, 3).toLowerCase() + name.substring(3);
            }
            // TODO: should be just return name? Support getting value from field or property that does not conform to java bean style?
        } else {
            // TODO: throw
            // throw new HibernateException( "Only property getter methods are expected to be passed" );
        }

        return propName;
    }
}
