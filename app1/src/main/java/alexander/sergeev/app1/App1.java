package alexander.sergeev.app1;

import alexander.sergeev.common.impl.ConsoleOutputProvider;
import alexander.sergeev.common.impl.LongInputReader;
import alexander.sergeev.common.io.InputReader;
import alexander.sergeev.common.io.OutputProvider;

import java.util.Scanner;

public class App1 {

    private final InputReader<Long> longReader;
    private final OutputProvider output;
    private final Calculator calculator;

    public App1(InputReader<Long> longReader,
                OutputProvider output,
                Calculator calculator) {
        this.longReader = longReader;
        this.output = output;
        this.calculator = calculator;
    }

    public void run() {
        long a = longReader.read();
        long b = longReader.read();

        output.print(calculator.compare(a, b));
        output.print(calculator.add(a, b));
        output.print(calculator.subtract(a, b));
        output.print(calculator.multiply(a, b));
        output.print(calculator.divide(a, b));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputReader<Long> longReader = new LongInputReader(scanner);
        OutputProvider output = new ConsoleOutputProvider();
        Calculator calculator = new LongCalculator();

        new App1(longReader, output, calculator).run();
    }
}