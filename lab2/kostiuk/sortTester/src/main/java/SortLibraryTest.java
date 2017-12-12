import com.hillel.sorters.Sorters;

import java.util.Arrays;
import java.util.function.Consumer;

public class SortLibraryTest {


    private final static String BUBBLE_SORT      = "Bubble sort";
    private final static String QUICK_ASYNC_SORT = "Quick async sort";

    public static void main(String[] args) {
        System.out.println(" 1000 values");
        int[] basicArray = randomArrayGenerator(1000_000, 1000);
        sortAndPrintResult(BUBBLE_SORT, arrayFullCopy(basicArray), Sorters::bubbleSort);
        sortAndPrintResult(QUICK_ASYNC_SORT, arrayFullCopy(basicArray), Sorters::quickSortAsync);

        System.out.println(" 10_000 values");
        basicArray = randomArrayGenerator(1000_000, 10_000);
        sortAndPrintResult(BUBBLE_SORT, arrayFullCopy(basicArray), Sorters::bubbleSort);
        sortAndPrintResult(QUICK_ASYNC_SORT, arrayFullCopy(basicArray), Sorters::quickSortAsync);
    }

    private static int[] randomArrayGenerator(int maxValue, int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (int) (Math.random() * maxValue);
        }
        return result;
    }

    private static int[] arrayFullCopy(int[] basicArray) {
        return Arrays.copyOf(basicArray, basicArray.length);
    }

    public static void sortAndPrintResult(String type, int[] array, Consumer<int[]> sorter) {
        long timeCutOne = System.currentTimeMillis();
        sorter.accept(array);
        long timeCutTwo = System.currentTimeMillis();
        printResultWithTime(type, array, timeCutTwo - timeCutOne);
    }

    public static void printResultWithTime(String type, int[] array, long sortedTime) {
        System.out.println("Type: " + type + "; Result time:" + sortedTime + " ms; \n" + Arrays.toString(array));
    }


}
