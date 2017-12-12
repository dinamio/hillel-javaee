import com.barmin.BubbleQuickSort;

import java.util.Random;

public class Main {
    public static void main(String[] arg){
        int[] arr = new int[10];
        Random random = new Random();
        for(int i = 0; i<arr.length; i++){
            arr[i] = Math.abs(random.nextInt()%10);
        }
        for(int a : arr){
            System.out.print(a+" ");
        }
        System.out.println();
        BubbleQuickSort.bubbleSort(arr);
        for(int a : arr){
            System.out.print(a+" ");
        }
    }
}
