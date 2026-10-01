package alexander.sergeev.app2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("StringComparatorImpl")
class StringComparatorImplTest {

    private StringComparatorImpl comparator;

    @BeforeEach
    void setUp() {
        comparator = new StringComparatorImpl();
    }

    @ParameterizedTest(name = "compare(\"{0}\", \"{1}\") = \"{2}\"")
    @CsvSource({
            "hello, hello, 'Строки идентичны'",
            "hello, world, 'Строки неидентичны'",
            "Hello, hello, 'Строки неидентичны'",
            "'  a', 'a', 'Строки неидентичны'",
            "'a b', 'a b', 'Строки идентичны'",
            "'123', '123', 'Строки идентичны'",
            "'!@#', '!@#', 'Строки идентичны'",
            "'привет', 'привет', 'Строки идентичны'"
    })
    void shouldCompareRegularStrings(String a, String b, String expected) {
        assertThat(comparator.compare(a, b)).isEqualTo(expected);
    }

    @Test
    @DisplayName("two empty strings are equal")
    void shouldTreatTwoEmptyStringsAsIdentical() {
        assertThat(comparator.compare("", "")).isEqualTo("Строки идентичны");
    }

    @Test
    @DisplayName("empty vs non-empty are not equal")
    void shouldTreatEmptyAndNonEmptyAsNotIdentical() {
        assertThat(comparator.compare("", "x")).isEqualTo("Строки неидентичны");
    }

    @Test
    @DisplayName("two nulls are equal")
    void shouldTreatTwoNullsAsIdentical() {
        assertThat(comparator.compare(null, null)).isEqualTo("Строки идентичны");
    }

    @Test
    @DisplayName("null vs string are not equal")
    void shouldTreatNullAndNonNullAsNotIdentical() {
        assertThat(comparator.compare(null, "x")).isEqualTo("Строки неидентичны");
    }

    @Test
    @DisplayName("string vs null are not equal")
    void shouldTreatNonNullAndNullAsNotIdentical() {
        assertThat(comparator.compare("x", null)).isEqualTo("Строки неидентичны");
    }

    @Test
    @DisplayName("null vs empty are not equal")
    void shouldTreatNullAndEmptyAsNotIdentical() {
        assertThat(comparator.compare(null, "")).isEqualTo("Строки неидентичны");
    }
}