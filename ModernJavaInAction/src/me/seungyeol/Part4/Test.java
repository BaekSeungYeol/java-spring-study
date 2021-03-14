package me.seungyeol.Part4;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        List<String> title = List.of("Java8", "In", "Action");
        java.util.stream.Stream<String> s = title.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);
    }
}
