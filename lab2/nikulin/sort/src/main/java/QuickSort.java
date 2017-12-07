import java.util.Collections;
import java.util.List;

public class QuickSort {

    private static List<Number> locallist;
    private static Number[] localarray;

    public static List<Number> sortList(List<? extends Number> list){

        int startIndex = 0;
        locallist = (List<Number>) list;
        int endIndex = list.size() - 1;
        doSortList(startIndex, endIndex);

        return locallist;
    }

    private static void doSortList(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (locallist.get(i).doubleValue() <= locallist.get(cur).doubleValue())) {
                i++;
            }
            while (j > cur && (locallist.get(cur).doubleValue() <= locallist.get(j).doubleValue())) {
                j--;
            }
            if (i < j) {
                double temp = locallist.get(i).doubleValue();
                Collections.swap(locallist, i, j);
                locallist.set(j, temp);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSortList(start, cur);
        doSortList(cur+1, end);
    }

    public static Number[] sortArray(Number[] array){

        int startIndex = 0;
        localarray = new Number[array.length];
        System.arraycopy(array, 0, localarray, 0, array.length);
        int endIndex = array.length - 1;
        doSortArray(startIndex, endIndex);

        return localarray;
    }

    private static void doSortArray(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (localarray[i].doubleValue() <= localarray[cur].doubleValue())) {
                i++;
            }
            while (j > cur && (localarray[cur].doubleValue() <= localarray[j].doubleValue())) {
                j--;
            }
            if (i < j) {
                Number temp = localarray[i];
                localarray[i] = localarray[j];
                localarray[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSortArray(start, cur);
        doSortArray(cur+1, end);
    }
}
