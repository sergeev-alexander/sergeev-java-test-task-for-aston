package alexander.sergeev.common.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LongInputReader")
class LongInputReaderTest {

    private LongInputReader readerWith(String input) {
        return new LongInputReader(new Scanner(new ByteArrayInputStream(input.getBytes())));
    }

    @ParameterizedTest(name = "reads \"{0}\"")
    @ValueSource(strings = {
            "42",
            "-42",
            "0",
            "9223372036854775807",
            "-9223372036854775808"
    })
    void readsValidLongValues(String input) {
        assertThat(readerWith(input + "\n").read())
                .isEqualTo(Long.parseLong(input));
    }

    @Test
    @DisplayName("trims whitespace")
    void trimsWhitespace() {
        assertThat(readerWith("  7  \n").read()).isEqualTo(7L);
    }

    @ParameterizedTest(name = "rejects \"{0}\"")
    @ValueSource(strings = {
            "abc",
            "",
            "3.14",
            "12a",
            "a12",
            "-5-",
            "+-5",
            "9223372036854775808",
            "-9223372036854775809"
    })
    void rejectsInvalidInputs(String input) {
        assertThatThrownBy(() -> readerWith(input + "\n").read())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid input")
                .hasCauseInstanceOf(NumberFormatException.class);
    }

    @Test
    @DisplayName("reads consecutive values")
    void readsConsecutiveValues() {
        LongInputReader reader = readerWith("10\n20\n");
        assertThat(reader.read()).isEqualTo(10L);
        assertThat(reader.read()).isEqualTo(20L);
    }
}