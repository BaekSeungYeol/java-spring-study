package me.seungyeol.Part4;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {
//        List<Dish> menu = new ArrayList<>();
//        List<Dish> lowCaloricDishes = new ArrayList<>();
//        for(Dish dish : menu) {
//            if(dish.getCalories() < 400) {
//                lowCaloricDishes.add(dish);
//            }
//        }
//        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
//            @Override
//            public int compare(Dish o1, Dish o2) {
//                return Integer.compare(o1.getCalories(), o2.getCalories());
//            }
//        });
//        List<String> lowCaloricDishesName = new ArrayList<>();
//        for(Dish dish : lowCaloricDishes) {
//            lowCaloricDishesName.add(dish.getName());
//        }
//
//        // Stream
//        menu.stream()
//                .filter(d -> d.getCalories() < 400)
//                .sorted(Comparator.comparing(Dish::getCalories))
//                .map(Dish::getName)
//                .collect(Collectors.toList());
//
//        List<String> threeHighCaloricDishNames =
//                menu.stream()
//                .filter(dish -> dish.getCalories() > 300)
//                .map(Dish::getName)
//                .limit(3)
//                .collect(Collectors.toList());
//
//        List<String> names =
//                menu.stream()
//                .filter(dish ->{
//                    System.out.println("filtering:" + dish.getName());
//                    return dish.getCalories() > 300;
//                })
//                .map(dish -> {
//                    System.out.println("mapping:" + dish.getName());
//                    return dish.getName();
//                })
//                .limit(3)
//                .collect(Collectors.toList());
//
//        menu.stream().forEach(System.out::println);
//
//        menu.stream().takeWhile(dish -> dish.getCalories() < 320).collect(Collectors.toList());
//        menu.stream().dropWhile(dish -> dish.getCalories() < 320).collect(Collectors.toList());
//
//        List<Integer> dishNameLengths = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
//
//
//        List<String> words = Arrays.asList("Modern", "Java", "In" ,"Action");
//        words.stream()
//                .map(word -> word.split(""))
//                .flatMap(Arrays::stream)
//                .collect(Collectors.toList());


        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);

        numbers1.stream().flatMap(n -> numbers2.stream().filter(m -> (n+m)%3 == 0).map(m -> new Integer[]{n,m}))
                .forEach((num) -> {
                    System.out.println(num[0] + " " + num[1]);
                });
    }

    public static class Dish {
        int calories;
        String name;

        public int getCalories() {
            return calories;
        }

        public String getName() {
            return name;
        }
    }
}
