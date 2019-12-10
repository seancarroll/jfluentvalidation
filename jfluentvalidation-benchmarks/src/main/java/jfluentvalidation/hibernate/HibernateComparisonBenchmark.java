package jfluentvalidation.hibernate;

import jfluentvalidation.validators.DefaultValidator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HibernateComparisonBenchmark {

    private static Person p = new Person("", -1);
    private Validator hibernateValidator;
    private DefaultValidator<Person> fluentValidator;

    @Setup
    public void prepare() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        hibernateValidator = factory.getValidator();

        fluentValidator = new PersonValidator();
    }


//    @Benchmark
//    public void hibernate() {
//        hibernateValidator.validate( p );
//    }

    @Benchmark
    public void fluent() {
        fluentValidator.validate(p);
    }

    static class Person {

        @NotNull
        @Size(min = 2, max = 14)
        private String name;

        @Positive
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private class PersonValidator extends DefaultValidator<Person> {

        PersonValidator() {
            ruleForString(Person::getName).isNotNull().length(2, 14);
            ruleForInteger(Person::getAge).isPositive();
        }
    }

    public static void main(String[] args) throws Exception {
        new Runner(new OptionsBuilder()
            .include(HibernateComparisonBenchmark.class.getSimpleName())
            .forks(1)
            .warmupIterations(5)
            .measurementIterations(5)
            .build())
            .run();
    }
}
