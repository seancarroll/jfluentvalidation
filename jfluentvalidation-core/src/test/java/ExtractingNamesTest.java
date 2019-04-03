import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// TODO: delete
public class ExtractingNamesTest {

//    public static void main(String[] args) {
//
//        BusinessObject bo = new BusinessObject();
//
//        System.out.println(printPropertyName(bo::getProperty1));
//        System.out.println(printPropertyName(bo::getProperty2));
//
//        System.out.println(printPropertyName(bo::setProperty1));
//        System.out.println(printPropertyName(bo::setProperty2));
//    }

    @Test
    void test() {
        BusinessObject bo = new BusinessObject();

        Method m = checkAndExtractLambdaMethod(() -> bo.getProperty1());

        checkAndExtractLambdaMethodFromFunction(b -> b.getProperty1());

        System.out.println(printPropertyName(() -> bo.getProperty1()));
        System.out.println(printPropertyName(bo::getProperty1));

        System.out.println(printPropertyName(bo::getProperty2));

        System.out.println(printPropertyName(bo::setProperty1));
        System.out.println(printPropertyName(bo::setProperty2));
    }

    private static <t> String printPropertyName(SerializableSupplier<t> getter) {
        return getter.method().getName();
    }

    private static <t> String printPropertyName(SerializableConsumer<t> setter) {
        return setter.method().getName();
    }


//    interface LambdaReflection {
//
//        default SerializedLambda serialized() {
//            try {
//                Method writeReplace = getClass().getDeclaredMethod("writeReplace");
//                writeReplace.setAccessible(true);
//                SerializedLambda sl = (SerializedLambda) writeReplace.invoke(this);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public static Method checkAndExtractLambdaMethod(SerializableSupplier supplier) {
        try {
            // get serialized lambda
            Object serializedLambda = null;
            for (Class<?> clazz = supplier.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
                try {
                    Method replaceMethod = clazz.getDeclaredMethod("writeReplace");
                    replaceMethod.setAccessible(true);
                    Object serialVersion = replaceMethod.invoke(supplier);

                    // check if class is a lambda function
                    if (serialVersion.getClass().getName().equals("java.lang.invoke.SerializedLambda")) {

                        // check if SerializedLambda class is present
                        try {
                            Class.forName("java.lang.invoke.SerializedLambda");
                        }
                        catch (Exception e) {
                            throw new UnsupportedOperationException("User code tries to use lambdas, but framework is running with a Java version < 8");
                        }
                        serializedLambda = serialVersion;
                        break;
                    }
                }
                catch (NoSuchMethodException e) {
                    // thrown if the method is not there. fall through the loop
                }
            }

            // not a lambda method -> return null
            if (serializedLambda == null) {
                return null;
            }

            // find lambda method
            Method implClassMethod = serializedLambda.getClass().getDeclaredMethod("getImplClass");
            Method implMethodNameMethod = serializedLambda.getClass().getDeclaredMethod("getImplMethodName");

            String className = (String) implClassMethod.invoke(serializedLambda);
            String methodName = (String) implMethodNameMethod.invoke(serializedLambda);

            Class<?> implClass = Class.forName(className.replace('/', '.'), true, Thread.currentThread().getContextClassLoader());

            Method[] methods = implClass.getDeclaredMethods();
            Method parameterizedMethod = null;
            for (Method method : methods) {
                if(method.getName().equals(methodName)) {
                    if(parameterizedMethod != null) {
                        // It is very unlikely that a class contains multiple e.g. "lambda$2()" but its possible
                        // Actually, the signature need to be checked, but this is very complex
                        throw new Exception("Lambda method name is not unique.");
                    }
                    else {
                        parameterizedMethod = method;
                    }
                }
            }
            if (parameterizedMethod == null) {
                throw new Exception("No lambda method found.");
            }
            return parameterizedMethod;
        }
        catch (Exception e) {
            throw new RuntimeException("Could not extract lambda method out of function: " + e.getClass().getSimpleName() + " - " + e.getMessage(), e);
        }
    }

    public static Method checkAndExtractLambdaMethodFromFunction(SerializableFunction<BusinessObject, String> function) {
        try {
            // get serialized lambda
            Object serializedLambda = null;
            for (Class<?> clazz = function.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
                try {
                    Method replaceMethod = clazz.getDeclaredMethod("writeReplace");
                    replaceMethod.setAccessible(true);
                    Object serialVersion = replaceMethod.invoke(function);

                    // check if class is a lambda function
                    if (serialVersion.getClass().getName().equals("java.lang.invoke.SerializedLambda")) {

                        // check if SerializedLambda class is present
                        try {
                            Class.forName("java.lang.invoke.SerializedLambda");
                        }
                        catch (Exception e) {
                            throw new UnsupportedOperationException("User code tries to use lambdas, but framework is running with a Java version < 8");
                        }
                        serializedLambda = serialVersion;
                        break;
                    }
                }
                catch (NoSuchMethodException e) {
                    // thrown if the method is not there. fall through the loop
                }
            }

            // not a lambda method -> return null
            if (serializedLambda == null) {
                return null;
            }

            // find lambda method
            Method implClassMethod = serializedLambda.getClass().getDeclaredMethod("getImplClass");
            Method implMethodNameMethod = serializedLambda.getClass().getDeclaredMethod("getImplMethodName");

            String className = (String) implClassMethod.invoke(serializedLambda);
            String methodName = (String) implMethodNameMethod.invoke(serializedLambda);

            Class<?> implClass = Class.forName(className.replace('/', '.'), true, Thread.currentThread().getContextClassLoader());

            Method[] methods = implClass.getDeclaredMethods();
            Method parameterizedMethod = null;
            for (Method method : methods) {
                if(method.getName().equals(methodName)) {
                    if(parameterizedMethod != null) {
                        // It is very unlikely that a class contains multiple e.g. "lambda$2()" but its possible
                        // Actually, the signature need to be checked, but this is very complex
                        throw new Exception("Lambda method name is not unique.");
                    }
                    else {
                        parameterizedMethod = method;
                    }
                }
            }
            if (parameterizedMethod == null) {
                throw new Exception("No lambda method found.");
            }
            return parameterizedMethod;
        }
        catch (Exception e) {
            throw new RuntimeException("Could not extract lambda method out of function: " + e.getClass().getSimpleName() + " - " + e.getMessage(), e);
        }
    }

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

        default Class getContainingClass() {
            try {
                String className = serialized().getImplClass().replaceAll("/", ".");
                return Class.forName(className);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        default Method method() {
            SerializedLambda lambda = serialized();
            Class containingClass = getContainingClass();
            return Arrays.asList(containingClass.getDeclaredMethods())
                .stream()
                .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
                .findFirst()
                .orElseThrow(UnableToGuessMethodException::new);
        }

        class UnableToGuessMethodException extends RuntimeException {}
    }

    interface SerializableSupplier<t> extends Supplier<t>, Serializable, MethodReferenceReflection {}

    interface SerializableConsumer<t> extends Consumer<t>, Serializable, MethodReferenceReflection {}

    interface SerializableFunction<T, R> extends Function<T, R>, Serializable {}

    static class BusinessObject {

        private String property1;
        private int property2;

        public String getProperty1() {
            return property1;
        }

        public void setProperty1(String property1) {
            this.property1 = property1;
        }

        public int getProperty2() {
            return property2;
        }

        public void setProperty2(int property2) {
            this.property2 = property2;
        }
    }
}
