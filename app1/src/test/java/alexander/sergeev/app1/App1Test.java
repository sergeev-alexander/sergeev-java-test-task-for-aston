package alexander.sergeev.app1;

import alexander.sergeev.common.io.InputReader;
import alexander.sergeev.common.io.OutputProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("App1")
class App1Test {

    @Mock
    InputReader<Long> reader;
    @Mock
    OutputProvider output;
    @Mock
    Calculator calculator;

    @InjectMocks
    App1 app;

    @Test
    @DisplayName("run() prints five results in order")
    void shouldExecuteAllOperationsInOrder() {
        when(reader.read()).thenReturn(10L, 2L);
        when(calculator.compare(10L, 2L)).thenReturn("a > b");
        when(calculator.add(10L, 2L)).thenReturn(12L);
        when(calculator.subtract(10L, 2L)).thenReturn(8L);
        when(calculator.multiply(10L, 2L)).thenReturn(20L);
        when(calculator.divide(10L, 2L)).thenReturn(5.0);

        app.run();

        InOrder inOrder = inOrder(output);
        inOrder.verify(output).print("a > b");
        inOrder.verify(output).print(12L);
        inOrder.verify(output).print(8L);
        inOrder.verify(output).print(20L);
        inOrder.verify(output).print(5.0);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    @DisplayName("run() reads exactly two numbers")
    void shouldReadExactlyTwoNumbers() {
        when(reader.read()).thenReturn(1L, 2L);
        when(calculator.divide(anyLong(), anyLong())).thenReturn(0.5);

        app.run();

        verify(reader, times(2)).read();
        verifyNoMoreInteractions(reader);
    }

    @Test
    @DisplayName("divide by zero propagates")
    void shouldThrowUpDivideByZero() {
        when(reader.read()).thenReturn(10L, 0L);
        when(calculator.divide(10L, 0L))
                .thenThrow(new ArithmeticException("Division by zero"));

        assertThatThrownBy(app::run).isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("Division by zero");
    }

    @Test
    @DisplayName("overflow propagates")
    void shouldThrowUpOverflow() {
        when(reader.read()).thenReturn(Long.MAX_VALUE, 1L);
        when(calculator.add(Long.MAX_VALUE, 1L))
                .thenThrow(new ArithmeticException("long overflow"));

        assertThatThrownBy(app::run).isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("overflow");
    }
}