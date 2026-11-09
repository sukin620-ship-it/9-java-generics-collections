package com.example.task01;

import java.io.IOException;
import java.io.InputStream;

public class Task01Main {
    public static void main(String[] args) throws IOException {


        Pair pair = Pair.of(1, "hello");
        Integer i = (Integer) pair.getFirst(); // 1
        String s = pair.getSecond().toString(); // "hello"
        Pair<Integer, String> pair2 = Pair.of(1, "hello");

        pair.ifPresent((first, second) -> {
            System.out.println(first);
            System.out.println(second);
        });

        Pair<Integer, String> pair22 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!


    }

}
