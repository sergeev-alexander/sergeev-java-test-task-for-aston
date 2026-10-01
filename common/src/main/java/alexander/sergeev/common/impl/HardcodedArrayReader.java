package alexander.sergeev.common.impl;

import alexander.sergeev.common.io.InputReader;

public class HardcodedArrayReader implements InputReader<int[]> {

    @Override
    public int[] read() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
}