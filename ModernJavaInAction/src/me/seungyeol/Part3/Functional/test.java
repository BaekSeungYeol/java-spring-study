package me.seungyeol.Part3.Functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class test {

    public static void main(String[] args) {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("test","bye","hi"));
        List<String> filtered = filter(list, nonEmptyStringPredicate);
        for (String s : filtered) {
            System.out.println(s);
        }


        forEach(Arrays.asList(1,2,3,4,5), System.out::println);


        List<Integer> map = map(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> i * 2);
        for (Integer integer : map) {
            System.out.println(integer );
        }

    }
    public static <T,R> List<R> map(List<T> list, Function<T,R> f) {
        List<R> result = new ArrayList<>();
        for(T t: list) {
            result.add(f.apply(t));
        }
        return result;
    }
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T t : list) {
            c.accept(t);
        }
    }

    public static <T> List<T> filter(List<T>list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }
}
