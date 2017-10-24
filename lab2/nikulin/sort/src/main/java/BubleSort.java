import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BubleSort {
    static List<? extends Number> sortList(List<? extends Number> list){
        boolean b = true;

        while (b){
            b = false;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).doubleValue() < list.get(i-1).doubleValue()) {
                    Collections.swap(list, i, (i-1));
                    b = true;
                }
            }
        }

        return list;
    }

    public static List<? extends Number> sortList(List<? extends Number> list, String order){
        boolean b = true;

        if(!Objects.equals(order, null)){
            if (order.equalsIgnoreCase("direct")){
                while (b){
                    b = false;
                    for (int i = 1; i < list.size(); i++) {
                        if (list.get(i).doubleValue() < list.get(i-1).doubleValue()) {
                            Collections.swap(list, i, (i-1));
                            b = true;
                        }
                    }
                }
            } else if (order.equalsIgnoreCase("revert")){
                while (b){
                    b = false;
                    for (int i = 1; i < list.size(); i++) {
                        if (list.get(i-1).doubleValue() < list.get(i).doubleValue()) {
                            Collections.swap(list, i, (i-1));
                            b = true;
                        }
                    }
                }
            }
        }

        return list;
    }

    public static Number[] sortArray(Number[] array){

        boolean b = true;
        while (b){
            b = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i].doubleValue() < array[i-1].doubleValue()){
                    Number temp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = temp;
                    b = true;
                }
            }
        }

        return array;
    }

    public static Number[] sortArray(Number[] array, String order){

        boolean b = true;
        if(Objects.equals(order, null)){
            if (order.equalsIgnoreCase("direct")) {
                while (b){
                    b = false;
                    for (int i = 1; i < array.length; i++) {
                        if (array[i].doubleValue() < array[i-1].doubleValue()){
                            Number temp = array[i].doubleValue();
                            array[i] = array[i-1];
                            array[i-1] = temp;
                            b = true;
                        }
                    }
                }
            } else if (order.equalsIgnoreCase("revert")) {
                while (b){
                    b = false;
                    for (int i = 1; i < array.length; i++) {
                        if (array[i-1].doubleValue() < array[i].doubleValue()){
                            Number temp = array[i].doubleValue();
                            array[i] = array[i-1];
                            array[i-1] = temp;
                            b = true;
                        }
                    }
                }
            }
        }

        return array;
    }
}
