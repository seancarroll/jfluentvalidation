package jfluentvalidation;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Objects;

import static java.util.Arrays.asList;

interface MethodReferenceReflection {

    //inspired by: http://benjiweber.co.uk/blog/2015/08/17/lambda-parameter-names-with-reflection/

    default SerializedLambda serialized() {
        try {
            Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Class<?> getContainingClass() {
        try {
            String className = serialized().getImplClass().replaceAll("/", ".");
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Method method() {
        SerializedLambda lambda = serialized();
        Class<?> containingClass = getContainingClass();
        return asList(containingClass.getDeclaredMethods())
            .stream()
            .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName())) // TODO: check parameter types to deal with overloads
            .findFirst()
            .orElseThrow(UnableToGuessMethodException::new);
    }

    class UnableToGuessMethodException extends RuntimeException {
    }
}
