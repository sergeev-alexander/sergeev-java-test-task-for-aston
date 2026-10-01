package alexander.sergeev.app2;

import alexander.sergeev.common.io.InputReader;
import alexander.sergeev.common.io.OutputProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("App2")
class App2Test {

    @Mock
    InputReader<String> reader;

    @Mock
    OutputProvider output;

    @Mock
    StringComparator comparator;

    @InjectMocks
    App2 app;

    @Test
    @DisplayName("run() reads two strings, prints one result")
    void shouldReadTwoStringsAndPrintOnce() {
        when(reader.read()).thenReturn("hello", "world");
        when(comparator.compare("hello", "world")).thenReturn("Строки неидентичны");

        app.run();

        verify(output, times(1)).print("Строки неидентичны");
        verify(reader, times(2)).read();
        verifyNoMoreInteractions(output);
    }
}