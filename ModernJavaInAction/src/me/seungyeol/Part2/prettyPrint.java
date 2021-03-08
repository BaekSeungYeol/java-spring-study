package me.seungyeol.Part2;

import java.util.Arrays;
import java.util.List;

public class prettyPrint {

    public static void main(String[] args) {
        List<FilteringApples.Apple> inventory = Arrays.asList(
                new FilteringApples.Apple(80, FilteringApples.Color.GREEN),
                new FilteringApples.Apple(155, FilteringApples.Color.GREEN),
                new FilteringApples.Apple(120, FilteringApples.Color.RED));
        
        AppleFormatter af = new AppleFancyFormatter();
        prettyPrintApple(inventory, af);
    }
    public interface AppleFormatter {
        String accept(FilteringApples.Apple a);
    }
    public static class AppleFancyFormatter implements AppleFormatter {
        @Override
        public String accept(FilteringApples.Apple a) {
            String ch = a.getWeight() > 150 ? "heavy" : "light";
            return "A " + ch + " " + a.getColor() + " apple";
        }

    }

    public static void prettyPrintApple(List<FilteringApples.Apple> inventory,AppleFormatter a) {
        for (FilteringApples.Apple apple : inventory) {
            String output = a.accept(apple);
            System.out.println(output);
        }
    }
}

