package jfluentvalidation.constraints.array;

import jfluentvalidation.constraints.array.length.ArrayExactLengthConstraint;
import jfluentvalidation.constraints.array.length.BooleanArrayExactLengthConstraint;
import jfluentvalidation.rules.PropertyRule;
import jfluentvalidation.validators.ConstraintContext;
import jfluentvalidation.validators.RuleOptions;
import jfluentvalidation.validators.ValidationContext;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class LengthBenchmark {

    public static class Foo {

        private boolean[] bar;

        public Foo(boolean[] bar) {
            this.bar = bar;
        }
    }

    ConstraintContext<Foo, boolean[]> ruleContext;
    BooleanArrayExactLengthConstraint booleanArrayExactLengthConstraintAlternative;
    ArrayExactLengthConstraint arrayExactLengthConstraint;
    BooleanArrayExactLengthConstraint booleanArrayExactLengthConstraint;

    boolean[] barr = new boolean[5];

    @Setup
    public void prepare() {
        Foo f = new Foo(new boolean[5]);

        PropertyRule propertyRule = new PropertyRule(foo -> f.bar, "bar", new RuleOptions());
        ruleContext = new ConstraintContext<>(new ValidationContext(f), propertyRule);

        booleanArrayExactLengthConstraintAlternative = new BooleanArrayExactLengthConstraint(5);
        arrayExactLengthConstraint = new ArrayExactLengthConstraint(5);
    }

    @Benchmark
    public void booleanArrayExactLengthConstraintAlternative() {
        booleanArrayExactLengthConstraintAlternative.isValid(ruleContext);
    }

    @Benchmark
    public void arrayExactLengthConstraint() {
        arrayExactLengthConstraint.isValid(ruleContext);
    }

//    @Benchmark
//    public void arrayReflectionGetLength() {
//        int l = Array.getLength(barr);
//    }

//    @Benchmark
//    public void getLength() {
//        int l = barr.length;
//    }

    public static void main(String[] args) throws Exception {
        new Runner(new OptionsBuilder()
            .include(LengthBenchmark.class.getSimpleName())
            .forks(1)
            .warmupIterations(2)
            .measurementIterations(5)
            .build())
            .run();
    }
}
