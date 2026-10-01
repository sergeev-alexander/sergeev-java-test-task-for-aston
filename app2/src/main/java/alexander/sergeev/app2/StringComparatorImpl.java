package alexander.sergeev.app2;

import java.util.Objects;

public class StringComparatorImpl implements StringComparator {

    @Override
    public String compare(String a, String b) {
        return (Objects.equals(a, b)) ? "Строки идентичны" : "Строки неидентичны";
    }
}