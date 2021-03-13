package me.seungyeol.Part3.ConstructorRef;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
    }

    public static Fruit giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase()).apply(weight);
    }

    interface TriFuction<T,U,V,R> {
        R apply(T t, U u, V v);
    }

    public static void main(String[] args) {
        Supplier<Apple> c1 = Apple::new;
        Apple apple = c1.get();
        System.out.println(apple.getWeight());

        Function<Integer,Apple> c2 = Apple::new;
        //Function<Integer,Apple> c2c = (weight) -> new Apple(weight);
        Apple apple2 = c2.apply(110);



        List<Integer> cands = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(cands,Apple::new);

        BiFunction<Integer,Color,Apple> c3 = Apple::new;
        //BiFunction<Integer,Color,Apple> c3c = (weight, color) -> new Apple(weight,color);
        Apple a3 = c3.apply(110, Color.GREEN);


        TriFuction<Integer,Integer,Integer,Integer> AppleFactory = (integer, integer2, integer3) -> integer*integer2*integer3;
        System.out.println(AppleFactory.apply(3,4,5));


        List<Apple> inventory = Arrays.asList(
                new Apple(3,Color.GRAY),
                new Apple(5,Color.GREEN)
        );

        inventory.sort(new AppleComparator());
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
        });
        inventory.sort((Apple a1, Apple a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed()
                .thenComparing(Apple::getTest));


        Predicate<Apple> redApple = a -> a.getColor().equals(Color.RED);
        Predicate<Apple> notRedApple = redApple.negate();

        notRedApple.and(a -> apple.getWeight() > 150).or(a -> apple.getColor().equals(Color.GRAY));
    }

    public static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return Integer.compare(o1.getWeight(),o2.getWeight());
        }
    }
    public static List<Apple> map(List<Integer> cands, Function<Integer,Apple> f) {
        List<Apple> ret = new ArrayList<>();
        for(Integer cand : cands) {
            ret.add(f.apply(cand));
        }
        return ret;
    }

    interface Fruit {

    }

    public static class Apple implements Fruit{

        private int weight = 0;
        private int test = 0;
        private Color color;

        public Apple() {}
        public Apple(int weight) {
            this.weight = weight;
        }
        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getTest() {
            return test;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }

    }
}
