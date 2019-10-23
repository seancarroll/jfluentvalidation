package jfluentvalidation;

import jfluentvalidation.constraints.array.LengthBenchmark;
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
public class UpperCaseBenchmark {

    private static final String val = "HELLO THIS IS A STRINg";

    @Setup
    public void prepare() {

    }


    @Benchmark
    public void allMatch() {
        isUpperAllMatch(val);
    }

    @Benchmark
    public void iterate() {
        isUpperIterate(val);
    }

    private static boolean isUpperAllMatch(CharSequence str) {
        return str.chars().allMatch(Character::isUpperCase);
    }


    public static boolean isUpperIterate(CharSequence str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isUpperCase(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

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
