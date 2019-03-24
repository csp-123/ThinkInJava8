package com.csp.s06;

import com.csp.s05.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Demo {
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

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish);

        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu);

        Optional<Dish> mostCalorieDish1 = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(mostCalorieDish1);

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

        // 多级分组
        System.out.println("================================");
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));
        System.out.println(dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);

        // 把收集器的结果转换为另一种类型
        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloricByType);

        // 与 groupingBy 联合使用的其他收集器的例子
        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);
    }
}
