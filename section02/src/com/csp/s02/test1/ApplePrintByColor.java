package com.csp.s02.test1;

import com.csp.s02.Apple;

public class ApplePrintByColor implements ApplePrint {
    @Override
    public String print(Apple apple) {
        return apple.getColor();
    }
}
