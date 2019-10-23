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
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class WhiteSpaceBenchmark {

    private static final String val = "averylongstringherewithoutwhitespaceuntiltheend ";

    @Setup
    public void prepare() {

    }

    @Benchmark
    public void anyMatch() {
        hasWhiteSpaceAnyMatch(val);
    }

    @Benchmark
    public void iterate() {
        hasWhiteSpaceIterate(val);
    }

    private static boolean hasWhiteSpaceAnyMatch(CharSequence str) {
        return str.chars().anyMatch(Character::isWhitespace);
    }


    public static boolean hasWhiteSpaceIterate(CharSequence str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
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
