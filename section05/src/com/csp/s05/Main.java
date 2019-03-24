package com.csp.s05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        System.out.println(menu);

        // first try
        List<String> threeHighCaloricDishNames = menu.stream().filter((Dish d) -> d.getCalories() > 300)
                .map(Dish::getName).limit(3).collect(Collectors.toList());

        System.out.println(threeHighCaloricDishNames);

        // 筛选前两个荤菜
        List<Dish> meatDishs = menu.stream().filter(d -> d.getType() == Dish.Type.MEAT).limit(2).collect(Collectors.toList());
        System.out.println(meatDishs);

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(dishNameLengths);

        System.out.println("----------------------------------------------------------");
        String[] arrayOfWords = {"Goodbye", "World"};
        List<String> uniqueCharacters = Arrays.stream(arrayOfWords).map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(uniqueCharacters);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        Optional<Integer> max = numbers.stream().reduce((a, b) -> a > b ? a : b);
        System.out.println(max.get());

        Optional<Integer> min = numbers.stream().reduce((a, b) -> a > b ? b : a);
        System.out.println(min.get());
    }
}
