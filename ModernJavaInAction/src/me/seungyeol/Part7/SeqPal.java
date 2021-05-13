package me.seungyeol.Part7;

import java.util.Collections;
import java.util.stream.Stream;

public class SeqPal {
    public static void main(String[] args) {
    }
    public long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i+1)
                .limit(n)
                .reduce(0L, Long::sum);
    }
    public long parallelSum(long n) {
        return Stream.iterate(0L, i -> i+1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
