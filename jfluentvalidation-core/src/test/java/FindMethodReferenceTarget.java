import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;

public class FindMethodReferenceTarget {
//    public static void main(String[] args) {
//        String s = "123";
//        Optional<Object> target = findTarget(s::charAt);
//        System.out.println(target.get().equals(s));
//
//        Object o = new FindMethodReferenceTarget();
//        target = findTarget(o::equals);
//        System.out.println(target.get().equals(o));
//    }


    @Test
    public void test() {
        String s = "123";
        Optional<Object> target = findTarget(s::charAt);
        System.out.println(target.get().equals(s));

        Object o = new FindMethodReferenceTarget();
        target = findTarget(o::equals);
        System.out.println(target.get().equals(o));

        DebuggableFunction<String, Character> f = w -> w.charAt(0);
        findTarget(f);
    }

    private static <T, R> Optional<Object> findTarget(DebuggableFunction<T, R> methodReference) {
        return getLambda(methodReference).map(l -> l.getCapturedArg(0));
    }

    private static Optional<SerializedLambda> getLambda(Serializable lambda) {
        for (Class<?> cl = lambda.getClass(); cl != null; cl = cl.getSuperclass()) {
            try {
                Method m = cl.getDeclaredMethod("writeReplace");
                m.setAccessible(true);
                Object replacement = m.invoke(lambda);
                if (!(replacement instanceof SerializedLambda)) {
                    break; // custom interface implementation
                }
                SerializedLambda l = (SerializedLambda) replacement;
                return Optional.of(l);
            } catch (NoSuchMethodException e) {
                // do nothing
            } catch (IllegalAccessException | InvocationTargetException e) {
                break;
            }
        }

        return Optional.empty();
    }

    @FunctionalInterface
    private interface DebuggableFunction<T, R> extends Serializable, Function<T, R> {}
}
