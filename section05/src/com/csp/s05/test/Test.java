package com.csp.s05.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> list = transactions.stream().filter(a -> a.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(list);
        // (2) 交易员都在哪些不同的城市工作过？
        List<String> citys = transactions.stream().map(a -> a.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(citys);
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> traders = transactions.stream().map(a -> a.getTrader()).filter(b -> b.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(traders);
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> names = transactions.stream().map(a -> a.getTrader().getName()).distinct()
                .sorted(Comparator.comparing(String::toString)).collect(Collectors.toList());
        System.out.println(names);

        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println("traderStr:" + traderStr);
        // (5) 有没有交易员是在米兰工作的？
        boolean has = transactions.stream().anyMatch(a -> a.getTrader().getCity().equals("Mario"));
        System.out.println(has);
        // (6) 打印生活在剑桥的交易员的所有交易额。
        List<Integer> values = transactions.stream().filter(a -> a.getTrader().getCity().equals("Cambridge"))
                .map(a -> a.getValue()).distinct().collect(Collectors.toList());
        System.out.println(values);
        // (7) 所有交易中，最高的交易额是多少？
        Optional<Integer> max = transactions.stream().map(a -> a.getValue()).reduce(Integer::max);
        System.out.println(max.get());
        // (8) 找到交易额最小的交易。
        Optional<Integer> min = transactions.stream().map(a -> a.getValue()).reduce(Integer::min);
        System.out.println(min.get());

        // 生成勾股数流
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );
        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
        System.out.println("=====================================");
        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                        .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples2.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


        // 斐波那契数列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
    }
}
