package com.csp.s02;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate ap) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if(ap.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
