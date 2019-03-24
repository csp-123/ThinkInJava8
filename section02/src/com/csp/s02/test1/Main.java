package com.csp.s02.test1;

import com.csp.s02.Apple;

import java.util.List;

public class Main {
    public static void prettyPrintApple(List<Apple> apples, ApplePrint ap) {
        for (Apple apple : apples) {
            String output = ap.print(apple);
            System.out.println(output);
        }
    }
}
