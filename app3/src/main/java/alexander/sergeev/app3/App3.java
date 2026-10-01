package alexander.sergeev.app3;

import alexander.sergeev.common.impl.ConsoleOutputProvider;
import alexander.sergeev.common.impl.HardcodedArrayReader;
import alexander.sergeev.common.io.InputReader;
import alexander.sergeev.common.io.OutputProvider;

public class App3 {

    private final InputReader<int[]> arrayReader;
    private final OutputProvider output;
    private final ArrayFilter filter;

    public App3(InputReader<int[]> arrayReader,
                OutputProvider output,
                ArrayFilter filter) {
        this.arrayReader = arrayReader;
        this.output = output;
        this.filter = filter;
    }

    public void run() {
        int[] numbers = arrayReader.read();
        int[] filteredNumbers = filter.filter(numbers);

        for (int num : filteredNumbers) {
            output.print(num);
        }
    }

    public static void main(String[] args) {
        InputReader<int[]> arrayReader = new HardcodedArrayReader();
        OutputProvider output = new ConsoleOutputProvider();
        ArrayFilter filter = new EvenArrayFilter();

        new App3(arrayReader, output, filter).run();
    }
}