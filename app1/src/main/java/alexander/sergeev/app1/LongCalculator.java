package alexander.sergeev.app1;

public class LongCalculator implements Calculator {

    @Override
    public String compare(long a, long b) {
        return (a > b) ? "a > b" : (a < b) ? "a < b" : "a = b";
    }

    @Override
    public long add(long a, long b) {
        return Math.addExact(a, b);
    }

    @Override
    public long subtract(long a, long b) {
        return Math.subtractExact(a, b);
    }

    @Override
    public long multiply(long a, long b) {
        return Math.multiplyExact(a, b);
    }

    @Override
    public double divide(long a, long b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (double) a / b;
    }
}