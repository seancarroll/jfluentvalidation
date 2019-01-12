import org.junit.jupiter.api.Test;
import validators.Person;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class DebuggableBiConsumerTest {

    @Test
    public void test() {
        Person p = new Person("Sean", 32, "address");
        DebuggableSupplier<String> nameSupplier1 = () -> p.getName();
        DebuggableSupplier<String> nameSupplier2 = () -> { throw new RuntimeException(); };
        set(p, Person::setName, nameSupplier1);
        System.out.println(p.getName()); // prints MyName
        set(p, Person::setName, nameSupplier2); // throws exception with message
        System.out.println(p.getName()); // Does not execute
    }

    interface DebuggableBiConsumer<A, B> extends BiConsumer<A, B>, Serializable {}

    interface DebuggableSupplier<A> extends Supplier<A>, Serializable {}

    private static <E, V> void set(E o, DebuggableBiConsumer<E, V> setter, DebuggableSupplier<V> valueSupplier) {
        try {
            setter.accept(o, valueSupplier.get());
            String s = name(valueSupplier);
            if (s == null) {

            }
        } catch (RuntimeException e) {
            String s = name(valueSupplier);
            throw new RuntimeException("Failed to set the value of "+name(setter), e);
        }
    }

    private static String name(DebuggableSupplier<?> supplier) {
        for (Class<?> cl = supplier.getClass(); cl != null; cl = cl.getSuperclass()) {
            try {
                Method m = cl.getDeclaredMethod("writeReplace");
                m.setAccessible(true);
                Object replacement = m.invoke(supplier);
                if(!(replacement instanceof SerializedLambda))
                    break;// custom interface implementation
                SerializedLambda l = (SerializedLambda) replacement;
                return l.getImplClass() + "::" + l.getImplMethodName();
            }
            catch (NoSuchMethodException e) {}
            catch (IllegalAccessException | InvocationTargetException e) {
                break;
            }
        }
        return "unknown property";
    }

    private static String name(DebuggableBiConsumer<?, ?> setter) {
        for (Class<?> cl = setter.getClass(); cl != null; cl = cl.getSuperclass()) {
            try {
                Method m = cl.getDeclaredMethod("writeReplace");
                m.setAccessible(true);
                Object replacement = m.invoke(setter);
                if(!(replacement instanceof SerializedLambda))
                    break;// custom interface implementation
                SerializedLambda l = (SerializedLambda) replacement;
                return l.getImplClass() + "::" + l.getImplMethodName();
            }
            catch (NoSuchMethodException e) {}
            catch (IllegalAccessException | InvocationTargetException e) {
                break;
            }
        }
        return "unknown property";
    }
}
