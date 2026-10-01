package alexander.sergeev.common.impl;

import alexander.sergeev.common.io.InputReader;

import java.util.Scanner;

public class LongInputReader implements InputReader<Long> {

    private final Scanner scanner;

    public LongInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Long read() {
        System.out.print("Enter an integer: ");
        String input = scanner.nextLine();
        try {
            return Long.parseLong(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: integer expected", e);
        }
    }
}