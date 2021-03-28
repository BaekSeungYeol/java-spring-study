package me.seungyeol.Part6;

import me.seungyeol.Part4.Stream;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class StreamData {
    public static void main(String[] args) {

        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);

        List<Dish> menu = new ArrayList<>(List.of(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        ));

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));

        String collect = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(collect);

        Optional<Dish> mostCaloriesDish = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        mostCaloriesDish.ifPresent(d -> System.out.println(d.getCalories()));

        Integer maxCal = menu.stream().collect(reducing(0, Dish::getCalories, Integer::max));
        System.out.println(maxCal);

        //int totalCalries = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        menu.stream().mapToInt(Dish::getCalories).sum();

        Map<CaloricLevel, List<Dish>> dishByCal = menu.stream().collect(groupingBy(StreamData::apply));

        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream().filter(d -> d.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
        System.out.println(caloricDishesByType);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                ));

        menu.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
        menu.stream().collect(groupingBy(Dish::getType, Collectors.collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        Map<Dish.Type, Set<CaloricLevel>> ret = menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }, toSet())));

        System.out.println(ret);


        menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT; }, toCollection(HashSet::new) )));

        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        List<Dish> dishes = partitionedMenu.get(true);

        menu.stream().collect(
                partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))
        );

        menu.stream().collect(
                partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                        Optional::get))
        );

        menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() <= 400)));
        menu.stream().collect(partitioningBy(Dish::isVegetarian,counting()));


    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2,n).boxed().collect(
                partitioningBy(this::isPrime));

    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }

        private static CaloricLevel apply(Dish dish) {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }

    public enum CaloricLevel { DIET, NORMAL, FAT }

    public static class Dish {
        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;

        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        public enum Type { MEAT, FISH, OTHER }

        @Override
        public String toString() {
            return name;
        }
    }

}
