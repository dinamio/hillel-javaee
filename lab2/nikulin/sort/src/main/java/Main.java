import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static Random random = new Random();
    public static void main(String[] args) {
        List<Integer> iList = new ArrayList<>();
        List<Double> dList = new ArrayList<>();
//        int[] ints = new int[100];
//        double[] doubles = new double[100];

        for (int i = 0; i < 100; i++) {
            iList.add(random.nextInt(1000));
            dList.add(random.nextDouble());
//            ints[i] = random.nextInt(1000);
//            doubles[i] = random.nextDouble();
        }

        System.out.println(QuickSort.sortList(iList));
    }
}
