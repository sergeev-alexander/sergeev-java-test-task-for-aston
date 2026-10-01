package alexander.sergeev.app3;

import alexander.sergeev.common.io.InputReader;
import alexander.sergeev.common.io.OutputProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@DisplayName("App3")
class App3Test {

    @Mock
    InputReader<int[]> arrayReader;

    @Mock
    OutputProvider output;

    @Mock
    ArrayFilter filter;

    @InjectMocks
    App3 app;

    @Test
    @DisplayName("run() filters and prints each element")
    void shouldFilterAndPrintEachElement() {
        when(arrayReader.read()).thenReturn(new int[]{1, 2, 3, 4});
        when(filter.filter(any(int[].class))).thenReturn(new int[]{2, 4});

        app.run();

        verify(output).print(2);
        verify(output).print(4);
        verify(output, times(2)).print(anyInt());
    }

    @Test
    @DisplayName("run() prints nothing for empty result")
    void shouldPrintNothingForEmptyResult() {
        when(arrayReader.read()).thenReturn(new int[]{1, 3});
        when(filter.filter(any(int[].class))).thenReturn(new int[]{});

        app.run();

        verifyNoInteractions(output);
    }

    @Test
    @DisplayName("run() reads input once")
    void shouldReadInputOnce() {
        when(arrayReader.read()).thenReturn(new int[]{2});
        when(filter.filter(any(int[].class))).thenReturn(new int[]{2});

        app.run();

        verify(arrayReader, times(1)).read();
    }

    @Test
    @DisplayName("null array from filter throws NPE")
    void shouldThrowsUpNpeFromFilter() {
        when(arrayReader.read()).thenReturn(null);
        when(filter.filter(null))
                .thenThrow(new NullPointerException("array must not be null"));

        assertThatThrownBy(app::run)
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("array");
    }
}