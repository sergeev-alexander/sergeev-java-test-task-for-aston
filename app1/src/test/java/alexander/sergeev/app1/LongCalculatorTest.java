package alexander.sergeev.app1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LongCalculator")
class LongCalculatorTest {

    private LongCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new LongCalculator();
    }

    @Nested
    @DisplayName("compare()")
    class CompareTests {

        @ParameterizedTest(name = "compare({0}, {1}) -> {2}")
        @CsvSource({
                " 5, 3, 'a > b'",
                " 3, 5, 'a < b'",
                " 4, 4, 'a = b'",
                "-1, -2, 'a > b'",
                "-5, 5, 'a < b'",
                " 0, 0, 'a = b'"
        })
        void shouldCompareRegularValues(long a, long b, String expected) {
            assertThat(calculator.compare(a, b)).isEqualTo(expected);
        }

        @ParameterizedTest(name = "compare({0}, {1}) -> {2}")
        @CsvSource({
                " 9223372036854775807, -9223372036854775808, 'a > b'",
                "-9223372036854775808, 9223372036854775807, 'a < b'",
                " 9223372036854775807, 9223372036854775807, 'a = b'",
                "-9223372036854775808, -9223372036854775808, 'a = b'"
        })
        void shouldCompareLongBoundaries(long a, long b, String expected) {
            assertThat(calculator.compare(a, b)).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("add()")
    class AddTests {

        @ParameterizedTest(name = "add({0}, {1}) = {2}")
        @CsvSource({
                " 2, 3, 5",
                "-2, -3, -5",
                " 0, 0, 0",
                "-2, 3, 1",
                " 5, -5, 0"
        })
        void shouldAddRegularValues(long a, long b, long expected) {
            assertThat(calculator.add(a, b)).isEqualTo(expected);
        }

        @Test
        @DisplayName("MAX and MIN boundaries")
        void addAtBoundariesIsSafe() {
            assertThat(calculator.add(Long.MAX_VALUE, 0)).isEqualTo(Long.MAX_VALUE);
            assertThat(calculator.add(Long.MIN_VALUE, 0)).isEqualTo(Long.MIN_VALUE);
        }

        @Test
        @DisplayName("overflow: MAX + 1")
        void addOverflowThrows() {
            assertThatThrownBy(() -> calculator.add(Long.MAX_VALUE, 1))
                    .isInstanceOf(ArithmeticException.class)
                    .hasMessageContaining("overflow");
        }
    }

    @Nested
    @DisplayName("subtract()")
    class SubtractTests {

        @ParameterizedTest(name = "subtract({0}, {1}) = {2}")
        @CsvSource({
                " 5, 3, 2",
                " 3, 5, -2",
                " 0, 0, 0",
                "-5, 3, -8",
                " 5, -3, 8"
        })
        void shouldSubtractRegularValues(long a, long b, long expected) {
            assertThat(calculator.subtract(a, b)).isEqualTo(expected);
        }

        @Test
        @DisplayName("MAX and MIN boundaries")
        void subtractAtBoundariesIsSafe() {
            assertThat(calculator.subtract(Long.MAX_VALUE, 0)).isEqualTo(Long.MAX_VALUE);
            assertThat(calculator.subtract(Long.MIN_VALUE, 0)).isEqualTo(Long.MIN_VALUE);
        }

        @Test
        @DisplayName("overflow: MIN - 1")
        void subtractOverflowThrows() {
            assertThatThrownBy(() -> calculator.subtract(Long.MIN_VALUE, 1))
                    .isInstanceOf(ArithmeticException.class)
                    .hasMessageContaining("overflow");
        }
    }

    @Nested
    @DisplayName("multiply()")
    class MultiplyTests {

        @ParameterizedTest(name = "multiply({0}, {1}) = {2}")
        @CsvSource({
                " 2, 3, 6",
                "-2, 3, -6",
                "-2, -3, 6",
                " 5, 0, 0",
                "-1, 1, -1"
        })
        void shouldMultiplyRegularValues(long a, long b, long expected) {
            assertThat(calculator.multiply(a, b)).isEqualTo(expected);
        }

        @Test
        @DisplayName("MAX and MIN boundaries")
        void multiplyAtBoundariesIsSafe() {
            assertThat(calculator.multiply(Long.MAX_VALUE, 1)).isEqualTo(Long.MAX_VALUE);
            assertThat(calculator.multiply(Long.MIN_VALUE, 1)).isEqualTo(Long.MIN_VALUE);
        }

        @Test
        @DisplayName("overflow: MAX * 2")
        void multiplyOverflowThrows() {
            assertThatThrownBy(() -> calculator.multiply(Long.MAX_VALUE, 2))
                    .isInstanceOf(ArithmeticException.class)
                    .hasMessageContaining("overflow");
        }

        @Test
        @DisplayName("overflow: MIN * -1")
        void multiplyMinByMinusOneThrows() {
            assertThatThrownBy(() -> calculator.multiply(Long.MIN_VALUE, -1))
                    .isInstanceOf(ArithmeticException.class)
                    .hasMessageContaining("overflow");
        }
    }

    @Nested
    @DisplayName("divide()")
    class DivideTests {

        @ParameterizedTest(name = "divide({0}, {1}) = {2}")
        @CsvSource({
                " 6, 3, 2.0",
                " 1, 2, 0.5",
                " 5, 2, 2.5",
                "-6, 3, -2.0",
                " 0, 5, 0.0"
        })
        void shouldDivideRegularValues(long a, long b, double expected) {
            assertThat(calculator.divide(a, b)).isEqualTo(expected);
        }

        @Test
        @DisplayName("division by zero throws")
        void divideByZeroThrows() {
            assertThatThrownBy(() -> calculator.divide(10, 0))
                    .isInstanceOf(ArithmeticException.class)
                    .hasMessageContaining("Division by zero");
        }

        @Test
        @DisplayName("MIN / -1 no overflow (double)")
        void divideMinByMinusOneDoesNotOverflow() {
            assertThat(calculator.divide(Long.MIN_VALUE, -1))
                    .isEqualTo(9.223372036854776E18);
        }
    }
}