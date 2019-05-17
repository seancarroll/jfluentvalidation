package jfluentvalidation.hibernate;

import jfluentvalidation.constraints.array.LengthBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HibernateComparisonBenchmark {

    @Setup
    public void prepare() {

    }

    public static void main(String[] args) throws Exception {
        new Runner(new OptionsBuilder()
            .include(LengthBenchmark.class.getSimpleName())
            .forks(1)
            .warmupIterations(5)
            .measurementIterations(5)
            .build())
            .run();
    }
}
