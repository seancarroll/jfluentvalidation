package jfluentvalidation.internal;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnsureTest {
    
    @Nested
    class NotNull {

        @Test
        void shouldNotThrowWhenNotNull() {
            assertDoesNotThrow(() -> Ensure.notNull(new Object()));
        }

        @Test
        void shouldThrowWhenNull() {
            assertThrows(NullPointerException.class, () -> Ensure.notNull(null));
        }

        @Test
        void shouldThrowWhenNullWithProvidedMessage() {
            assertThrows(NullPointerException.class, () -> Ensure.notNull(null, "Value should not be null"), "Value should not be null");
        }
    }

    @Nested
    class Positive {

        @Test
        void shouldNotThrowWhenIntIsGreaterThanZero() {
            assertDoesNotThrow(() -> Ensure.positive(1, "value"), "value should be positive.");
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, 0 })
        void shouldThrowWhenIntIsZeroOrNegative(int value) {
            assertThrows(IllegalArgumentException.class, () -> Ensure.positive(value, "value"), "value should be positive.");
        }

        @Test
        void shouldNotThrowWhenLongIsGreaterThanZero() {
            assertDoesNotThrow(() -> Ensure.positive(1L, "value"), "value should be positive.");
        }

        @ParameterizedTest
        @ValueSource(longs = { -1L, 0L })
        void shouldThrowWhenLongIsZeroOrNegative(long value) {
            assertThrows(IllegalArgumentException.class, () -> Ensure.positive(value, "value"), "value should be positive.");
        }
    }

    @Nested
    class Nonnegative {

        @ParameterizedTest
        @ValueSource(ints = { 0, 1 })
        void shouldNotThrowWhenIntIsGreaterOrEqualToZero(int value) {
            assertDoesNotThrow(() -> Ensure.nonnegative(value, "value"), "value should be non negative.");
        }

        @ParameterizedTest
        @ValueSource(ints = { -1 })
        void shouldThrowWhenIntIsLessThanZero(int value) {
            assertThrows(IllegalArgumentException.class, () -> Ensure.nonnegative(value, "value"), "value should be non negative.");
        }

        @ParameterizedTest
        @ValueSource(longs = { 0L, 1L })
        void shouldNotThrowWhenLongIsGreaterOrEqualToZero(long value) {
            assertDoesNotThrow(() -> Ensure.nonnegative(value, "value"), "value should be non negative.");
        }

        @ParameterizedTest
        @ValueSource(longs = { -1 })
        void shouldThrowWhenLongIsLessThanZero(long value) {
            assertThrows(IllegalArgumentException.class, () -> Ensure.nonnegative(value, "value"), "value should be non negative.");
        }
    }

    @Nested
    class Argument {

        @Test
        void shouldNotThrowWhenExpressionEvaluatesToTrue() {
            assertDoesNotThrow(() -> Ensure.argument(true));
        }

        @Test
        void shouldThrowWhenExpressionEvaluatesToFalse() {
            assertThrows(IllegalArgumentException.class, () -> Ensure.argument(false));
        }

        @Test
        void shouldThrowWhenExpressionEvaluatesToFalseWithProvidedMessage() {
            assertThrows(IllegalArgumentException.class, () -> Ensure.argument(false, "should not be false"), "should not be false");
        }
    }


}
