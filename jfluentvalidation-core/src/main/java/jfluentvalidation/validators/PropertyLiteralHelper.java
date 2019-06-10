package jfluentvalidation.validators;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

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
        // TODO: how can we create an instance even when no-arg constructor is not defined.
        try {
            // uses DEFAULT_CONSTRUCTOR
            // TODO: should I use a specific ConstructorStrategy such as ConstructorStrategy.Default.NO_CONSTRUCTORS?
            DynamicType.Builder<?> builder = new ByteBuddy().subclass(type.isInterface() ? Object.class : type);
            if (type.isInterface()) {
                builder = builder.implement(type);
            }

//        MethodCall.invoke(declaredConstructor).with("message")
//            .andThen(MethodDelegation.to(DefaultConstructorInterceptor.class));
//            .defineConstructor(Visibility.PUBLIC)
//                .intercept(MethodCall.invoke(type.getDeclaredConstructor()).onSuper())


            Class<?> proxyType = builder //new ByteBuddy().subclass( type )
                .implement(PropertyNameCapturer.class)
                .defineField("propertyName", String.class, Visibility.PRIVATE)
                .method(ElementMatchers.any()).intercept(MethodDelegation.to(PropertyNameCapturingInterceptor.class))
                .method(named("setPropertyName").or(named("getPropertyName"))).intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(PropertyLiteralHelper.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
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

            //return typed.newInstance();
        } catch (Exception e) { //InstantiationException | IllegalAccessException e) {
            throw new InvalidPropertyException("Couldn't instantiate proxy for property literal dereferencing", e);
        }
    }



//    assertj SoftProxies.java
//    private static <V> Class<? extends V> createSoftAssertionProxyClass(Class<V> assertClass) {
//        TypeCache.SimpleKey cacheKey = new TypeCache.SimpleKey(assertClass);
//        return (Class<V>) CACHE.findOrInsert(SoftProxies.class.getClassLoader(), cacheKey, () -> generateProxyClass(assertClass));
//    }
//
//    static <V> Class<? extends V> generateProxyClass(Class<V> assertClass) {
//        return BYTE_BUDDY.subclass(assertClass)
//            .defineField(ProxifyMethodChangingTheObjectUnderTest.FIELD_NAME,
//                ProxifyMethodChangingTheObjectUnderTest.class,
//                Visibility.PRIVATE)
//            .method(METHODS_CHANGING_THE_OBJECT_UNDER_TEST)
//            .intercept(PROXIFY_METHOD_CHANGING_THE_OBJECT_UNDER_TEST)
//            .defineField(ErrorCollector.FIELD_NAME, ErrorCollector.class, Visibility.PRIVATE)
//            .method(any().and(not(METHODS_CHANGING_THE_OBJECT_UNDER_TEST))
//                .and(not(METHODS_NOT_TO_PROXY)))
//            .intercept(ERROR_COLLECTOR)
//            .implement(AssertJProxySetup.class)
//            // set ProxifyMethodChangingTheObjectUnderTest and ErrorCollector fields on the generated proxy
//            .intercept(FieldAccessor.ofField(ProxifyMethodChangingTheObjectUnderTest.FIELD_NAME).setsArgumentAt(0)
//                .andThen(FieldAccessor.ofField(ErrorCollector.FIELD_NAME).setsArgumentAt(1)))
//            .make()
//            // Use ClassLoader of soft assertion class to allow ByteBuddy to always find it.
//            // This is needed in OSGI runtime when custom soft assertion is defined outside of assertj bundle.
//            .load(assertClass.getClassLoader(), classLoadingStrategy(assertClass))
//            .getLoaded();
//    }

}
