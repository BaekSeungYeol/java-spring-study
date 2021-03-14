package me.seungyeol.Part4;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {
        List<Dish> menu = new ArrayList<>();
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish dish : menu) {
            if(dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish dish : lowCaloricDishes) {
            lowCaloricDishesName.add(dish.getName());
        }

        // Stream
        menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

        List<String> threeHighCaloricDishNames =
                menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

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
