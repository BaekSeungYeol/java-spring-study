package me.seungyeol.Part5;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberStream {
    public static void main(String[] args) {
        List<Dish> menu = List.of(
                new Dish(314,"n1", true),
                new Dish(344,"n2", false)
        );

        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        java.util.stream.IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        int max = maxCalories.orElse(1);

        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        IntStream.rangeClosed(1,100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a,100)
                .mapToObj(
                        b -> new double[]{a,b,Math.sqrt(a*a + b*b)})
                        .filter(t -> t[2] % 1 == 0));

    }
    public static class Dish {
        int calories;
        String name;
        boolean vegetarian;

        public Dish(int calories, String name, boolean vegetarian) {
            this.calories = calories;
            this.name = name;
            this.vegetarian = vegetarian;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public String getName() {
            return name;
        }
    }
}
