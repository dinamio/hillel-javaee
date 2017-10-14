package com.hillel.kostiuk.fizzbuzz;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FizzBuzzTest {

    private static Map<Integer, String> participantHolder;

    public static void main(String[] args) throws IOException {
        participantHolder = new HashMap<>();
        participantHolder.put(3, "Fizz");
        participantHolder.put(5, "Buzz");
        printValues(0, 100);
    }

    private static void printValues(int from, int to) {
        IntStream.rangeClosed(from, to)
                .mapToObj(FizzBuzzTest::getComputationString)
                .forEach(System.out::println);
    }

    private static String getComputationString(int i) {
        StringBuilder result = new StringBuilder();
        participantHolder.keySet().stream()
                .filter(key -> i % key == 0)
                .map(participantHolder::get)
                .forEach(result::append);
        return (result.length() == 0 || i == 0) ? ("" + i) : result.toString();
    }

}
