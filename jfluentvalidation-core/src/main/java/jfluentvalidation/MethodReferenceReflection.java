package jfluentvalidation;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * inspired by: http://benjiweber.co.uk/blog/2015/08/17/lambda-parameter-names-with-reflection/
 */
interface MethodReferenceReflection {

    default SerializedLambda serialized() {
        try {
            Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Class<?> getContainingClass(SerializedLambda lambda) {
        try {
            String className = lambda.getImplClass().replace("/", ".");
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Method method() {
        SerializedLambda lambda = serialized();
        Class<?> containingClass = getContainingClass(lambda);
        return Arrays.stream(containingClass.getDeclaredMethods())
            .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName())) // TODO: check parameter types to deal with overloads
            .findFirst()
            .orElseThrow(UnableToGuessMethodException::new);
    }

    class UnableToGuessMethodException extends RuntimeException {
    }
}
