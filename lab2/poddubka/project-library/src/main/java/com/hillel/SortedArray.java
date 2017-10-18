package com.hillel;

/**
 * The Class sorts an integer array by methods <b>bubbleSort(int[] arr)</b> and <b>quickSort(int[] arr, int from, int to)</b>.
 * @author Poddubka Sergiy
 * @version 1.0
 */
public class SortedArray {

    /**
     * This method uses a bubble sort.
     * @param arr the integer array to be sorted
     * @see #bubbleSort(int[])
     */
    public static void bubbleSort(int[] arr) {
        int count = 0;
        while (count < arr.length) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int n = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = n;
                }
            }
            count++;
        }
    }

    /**
     * This method uses a quick sort.
     * @param arr the integer array to be sorted
     * @param from the index of the first element, inclusive, to be sorted
     * @param to the index of the first element, inclusive, to be sorted
     * @see #quickSort(int[], int, int)
     */
    public static void quickSort(int[] arr, int from, int to) {

        if (arr.length == 0) return;
        if (from >= to) return;

        int middle = from + (to - from) / 2;
        int pivot = arr[middle];

        int i = from;
        int j = to;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot)j--;

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        if (from < j)quickSort(arr, from, j);
        if (to > i) quickSort(arr, i, to);
    }
}
























