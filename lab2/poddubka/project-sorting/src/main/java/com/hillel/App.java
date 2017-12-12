package com.hillel;

import java.util.Arrays;

import static com.hillel.SortedArray.bubbleSort;
import static com.hillel.SortedArray.quickSort;

public class App {

    public static void main( String[] args ) {

        int[] arr1 = {34,5,22,34,64,24};
        System.out.println("Initial array: " + Arrays.toString(arr1));

        long startTime1 = System.currentTimeMillis();
        bubbleSort(arr1);
        long timeSpent1 = System.currentTimeMillis() - startTime1;

        System.out.println("Sorted array: " + Arrays.toString(arr1));
        System.out.println("The method bubbleSort(int[]arr) performed " + timeSpent1 + " ms");

        System.out.println();

        int[] arr2 = {34,5,22,34,64,24};
        System.out.println("Initial array: " + Arrays.toString(arr1));

        long startTime2 = System.currentTimeMillis();
        quickSort(arr2, 0, arr2.length - 1);
        long timeSpent2 = System.currentTimeMillis() - startTime2;

        System.out.println("Sorted array: " + Arrays.toString(arr1));
        System.out.println("The method quickSort(int[] arr, int from, int to) performed " + timeSpent2 + " ms");
    }
}
