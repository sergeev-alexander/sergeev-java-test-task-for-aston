package alexander.sergeev.app2;

import alexander.sergeev.common.impl.ConsoleOutputProvider;
import alexander.sergeev.common.impl.StringInputReader;
import alexander.sergeev.common.io.InputReader;
import alexander.sergeev.common.io.OutputProvider;

import java.util.Scanner;

public class App2 {

    private final InputReader<String> stringReader;
    private final OutputProvider output;
    private final StringComparator comparator;

    public App2(InputReader<String> stringReader,
                OutputProvider output,
                StringComparator comparator) {
        this.stringReader = stringReader;
        this.output = output;
        this.comparator = comparator;
    }

    public void run() {
        String a = stringReader.read();
        String b = stringReader.read();

        output.print(comparator.compare(a, b));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputReader<String> stringReader = new StringInputReader(scanner);
        OutputProvider output = new ConsoleOutputProvider();
        StringComparator comparator = new StringComparatorImpl();

        new App2(stringReader, output, comparator).run();
    }
}