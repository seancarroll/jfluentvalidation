package jfluentvalidation.validators;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.function.Function;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class PropertyLiteralHelper {

    public static <T> String getPropertyName(Class<T> type, Function<T, ?> propertyLiteral) {
        T capturer = getPropertyNameCapturer(type);
        propertyLiteral.apply(capturer);
        return ((PropertyNameCapturer) capturer).getPropertyName();
    }

    public static <T> String getPropertyName(T entity, Function<T, ?> propertyLiteral) {
        propertyLiteral.apply(entity);
        return ((PropertyNameCapturer) entity).getPropertyName();
    }

    public static <T> T getPropertyNameCapturer(Class<T> type) {
        DynamicType.Builder<?> builder = new ByteBuddy().subclass(type.isInterface() ? Object.class : type);
        if (type.isInterface()) {
            builder = builder.implement(type);
        }

        Class<?> proxyType = builder //new ByteBuddy().subclass( type )
            .implement(PropertyNameCapturer.class)
            .defineField("propertyName", String.class, Visibility.PRIVATE)
            .method(ElementMatchers.any()).intercept(MethodDelegation.to(PropertyNameCapturingInterceptor.class))
            .method(named("setPropertyName").or(named("getPropertyName"))).intercept(FieldAccessor.ofBeanProperty())
            .make()
            .load(PropertyLiteralHelper.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        try {
            // TODO: is there a way to either not force the class to have a public no-arg constructor?
            // It would be nice if we could support private no-arg constructor at the very least but my preference
            // would be not to require anything.

//            Constructor ctor = proxyType.getConstructor();
//            ctor.setAccessible(true);
//            return ctor.newInstance();
//
//            @SuppressWarnings("unchecked")
//            Class<T> typed = (Class<T>) proxyType;
//            Constructor<T> ctor = typed.getConstructor();
//            ctor.setAccessible(true);
//            return ctor.newInstance();
//            //return typed.newInstance();


            @SuppressWarnings("unchecked")
            Class<T> typed = (Class<T>) proxyType;
            return typed.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new InvalidPropertyException("Couldn't instantiate proxy for property literal dereferencing", e);
        }
    }

}
