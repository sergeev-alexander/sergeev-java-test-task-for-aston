package alexander.sergeev.common.impl;

import alexander.sergeev.common.io.OutputProvider;

public class ConsoleOutputProvider implements OutputProvider {

    @Override
    public void print(Object object) {
        System.out.println(object);
    }
}