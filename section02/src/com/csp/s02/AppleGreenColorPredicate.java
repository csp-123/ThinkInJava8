package com.csp.s02;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "Green".equals(apple.getColor());
    }
}
