package com.hillel.sorters;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Sorters {


    public static void bubbleSort(int[] unsortedArray) {
        for (int outIndex = unsortedArray.length - 1; outIndex > 1; outIndex--) {
            for (int inIndex = 0; inIndex < outIndex; inIndex++) {
                if (unsortedArray[inIndex] > unsortedArray[inIndex + 1]) {
                    swap(unsortedArray, inIndex, inIndex + 1);
                }
            }
        }
    }

    public static void quickSortAsync(int[] unsortedArray) {
        ForkJoinPool.commonPool().invoke(new QuickSortAction(unsortedArray));
    }

    private static class QuickSortAction extends RecursiveAction {

        private final int[] unsortedArray;
        private       int   left;
        private       int   right;


        QuickSortAction(int[] unsortedArray) {
            this.unsortedArray = unsortedArray;
            right = unsortedArray.length - 1;
        }

        QuickSortAction(int[] unsortedArray, int left, int right) {
            this.unsortedArray = unsortedArray;
            this.left = left;
            this.right = right;
        }

        protected void compute() {
            if (right - left <= 1) return;

            int dividerPosition = divideComputation(unsortedArray, left, right) - 1;

            QuickSortAction sortLeft = new QuickSortAction(unsortedArray, left, dividerPosition);
            QuickSortAction sortRight = new QuickSortAction(unsortedArray, dividerPosition, right);
            invokeAll(sortLeft, sortRight);
        }

        private int divideComputation(int[] unsorted, int left, int right) {
            int pivotValue = unsorted[right];
            int leftBound = left - 1;

            leftBound = moveIndexesTowardEachOther(unsorted, leftBound, right, pivotValue);
            swap(unsorted, leftBound, right);
            return leftBound;
        }


        private int moveIndexesTowardEachOther(int[] array, int left, int right, int pivotValue) {
            left = getBiggerValueIndex(array, left, pivotValue);
            right = getLessValueIndex(array, right, pivotValue);
            if (left <= right) {
                swap(array, left, right);
                return moveIndexesTowardEachOther(array, left, right, pivotValue);
            }
            return left;
        }

        private int getBiggerValueIndex(int[] array, int pos, int pivotValue) {
            if (array[++pos] >= pivotValue) {
                return pos;
            }
            return getBiggerValueIndex(array, pos, pivotValue);
        }

        private int getLessValueIndex(int[] array, int pos, int pivotValue) {
            if (pos <= 0 || array[--pos] <= pivotValue) {
                return pos;
            }
            return getLessValueIndex(array, pos, pivotValue);
        }

    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

}
