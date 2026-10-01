package alexander.sergeev.common.impl;

import alexander.sergeev.common.io.InputReader;

import java.util.Scanner;

public class StringInputReader implements InputReader<String> {

    private final Scanner scanner;

    public StringInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String read() {
        System.out.print("Enter a string: ");
        return scanner.nextLine();
    }
}