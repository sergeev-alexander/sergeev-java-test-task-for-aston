package alexander.sergeev.app3;

import java.util.Arrays;
import java.util.Objects;

public class EvenArrayFilter implements ArrayFilter {

    @Override
    public int[] filter(int[] array) {
        Objects.requireNonNull(array, "Array must not be null");
        return Arrays.stream(array)
                .filter(n -> n % 2 == 0)
                .toArray();
    }
}