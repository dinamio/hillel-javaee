import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static Random random = new Random();

    public static void main(String[] args) {
        List<Integer> iList = new ArrayList<>();
        List<Integer> iListq = new ArrayList<>();
        List<Double> dList = new ArrayList<>();
        List<Double> dListq = new ArrayList<>();
        Integer[] ints = new Integer[100];
        Integer[] intsq = new Integer[100];
        Double[] doubles = new Double[100];
        Double[] doublesq = new Double[100];

        for (int i = 0; i < 100; i++) {
            iList.add(random.nextInt(1000));
            iListq.add(random.nextInt(1000));
            dList.add(random.nextDouble());
            dListq.add(random.nextDouble());
            ints[i] = random.nextInt(1000);
            intsq[i] = random.nextInt(1000);
            doubles[i] = random.nextDouble();
            doublesq[i] = random.nextDouble();
        }

        for (int i = 0; i < 100; i++) {
            System.out.print(BubleSort.sortArray(ints)[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 100; i++) {
            System.out.print(BubleSort.sortArray(doubles)[i] + " ");
        }
        System.out.println(BubleSort.sortList(iList));
        System.out.println(BubleSort.sortList(dList));

        for (int i = 0; i < 100; i++) {
            System.out.print(QuickSort.sortArray(intsq)[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 100; i++) {
            System.out.print(QuickSort.sortArray(doublesq)[i] + " ");
        }
        System.out.println();
        System.out.println(QuickSort.sortList(iListq));
        System.out.println(QuickSort.sortList(dListq));
    }
}
