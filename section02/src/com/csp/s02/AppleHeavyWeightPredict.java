package com.csp.s02;

public class AppleHeavyWeightPredict implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return 150 < apple.getWeight();
    }
}
