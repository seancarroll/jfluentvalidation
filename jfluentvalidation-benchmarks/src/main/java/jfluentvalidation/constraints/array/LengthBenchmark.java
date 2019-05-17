package jfluentvalidation.constraints.array;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class LengthBenchmark {

//    public static class Foo {
//
//        private boolean[] bar;
//    }
//
//    RuleContext<Foo, boolean[]> ruleContext;
//    Foo f;
//    BooleanArrayExactLength objExactLength;
//    HasSameSizeAsBooleanArrayConstraint booleanArraySize;
//    BooleanArrayExactLengthConstraint booleanArrayExactLengthConstraint;
//
//    @Setup
//    public void prepare() {
//        f = new Foo();
//        f.bar = new boolean[5];
//
//        Function<Foo, boolean[]> func = foo -> f.bar;
//        PropertyRule propertyRule = new PropertyRule(func, "bar");
//        ruleContext = new RuleContext<>(new ValidationContext(f), propertyRule);
//
//        objExactLength = new BooleanArrayExactLength<Foo>(5);
//        booleanArraySize = new HasSameSizeAsBooleanArrayConstraint(5);
//        booleanArrayExactLengthConstraint = new BooleanArrayExactLengthConstraint(5);
//    }
//
//    @Benchmark
//    public void objWithCast() {
//        objExactLength.isValid(ruleContext);
//    }
//
//    @Benchmark
//    public void booleanArraySize() {
//        booleanArraySize.isValid(ruleContext);
//    }
//
//    @Benchmark
//    public void booleanArrayLengthConstraint() {
//        booleanArraySize.isValid(ruleContext);
//    }
//
//    public static void main(String[] args) throws Exception {
//        new Runner(new OptionsBuilder()
//            .include(LengthBenchmark.class.getSimpleName())
//            .forks(1)
//            .warmupIterations(5)
//            .measurementIterations(5)
//            .build())
//            .run();
//    }
}
